package cn.ken.questionansweringsystem.service.qa;

import cn.ken.questionansweringsystem.common.*;
import cn.ken.questionansweringsystem.common.Enum;
import cn.ken.questionansweringsystem.memorydb.ConfigurationDB;
import cn.ken.questionansweringsystem.memorydb.KnowledgeDB;
import cn.ken.questionansweringsystem.model.LogQaRecommend;
import cn.ken.questionansweringsystem.model.configuration.Configuration;
import cn.ken.questionansweringsystem.model.knowledge.Answer;
import cn.ken.questionansweringsystem.model.knowledge.ExtensionQuestion;
import cn.ken.questionansweringsystem.model.knowledge.Knowledge;
import cn.ken.questionansweringsystem.model.qa.QuestionAnswer;
import cn.ken.questionansweringsystem.model.qa.QuestionAnswerHistory;
import cn.ken.questionansweringsystem.model.qa.Retrieval;
import cn.ken.questionansweringsystem.utils.Base;
import cn.ken.questionansweringsystem.utils.IdWorker;
import cn.ken.questionansweringsystem.utils.RedisUtils;
import cn.ken.questionansweringsystem.utils.StringUtils;
import cn.ken.questionansweringsystem.utils.hanlp.CompareUtils;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.mining.word.WordInfo;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.ResultTerm;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.URLTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * author: shangkun <br/>
 * date: 2018/7/28 <br/>
 * what: BaseService <br/>
 */
@Service
public class QAServiceImpl extends Base implements QAService{
    @Autowired
    private RedisUtils redisUtils;

    private static final CompareUtils COMPARE_UTILS = new CompareUtils();

    @Override
    public QuestionAnswer qa(QuestionAnswer questionAnswer) throws Exception {
        questionAnswer.setRequestTime(new Date());
        //数据合法性校验
        if(!StringUtils.lengthCheck(questionAnswer.getQuestion(),0,500)){
            questionAnswer.setMessage("问题不能为空或长度超过500");
            return questionAnswer;
        }
        if(!StringUtils.lengthCheck(questionAnswer.getUserId(),0,50)){
            questionAnswer.setMessage("用户id不能为空或长度超过50");
            return questionAnswer;
        }
        if(questionAnswer.getChannelId()==null ||
                (questionAnswer.getChannelId()!= Enum.webChannel.getValue() && questionAnswer.getChannelId()!= Enum.appChannel.getValue() && questionAnswer.getChannelId()!= Enum.wechatChannel.getValue())){
            questionAnswer.setMessage("渠道id不能为空或渠道id不合法");
            return questionAnswer;
        }
        questionAnswer.setId(IdWorker.getInstance().nextId());
        //会话id生成
        if(StringUtils.isEmpty(questionAnswer.getSessionId())){
            questionAnswer.setSessionId(IdWorker.getInstance().nextId());
        }
        //问题理解
        questionUnderstand(questionAnswer);
        //信息检索
        informationRetrieval(questionAnswer);
        //答案生成
        answerGenerate(questionAnswer);

        questionAnswer.setSegmentResult(null);
        questionAnswer.setDeleteStopWordsResult(null);
        if(questionAnswer.isDebugMode()){
            return questionAnswer;
        }else{
            //非调式模式下 清空分词数据
            questionAnswer.setSegmentResultToString(null);
            questionAnswer.setDeleteStopWordsResultToString(null);
            //问答历史不展示
            questionAnswer.setHistory(null);
        }
        questionAnswer.setResponseTime(new Date());
        return questionAnswer;
    }

    @Override
    public List<String> suggest(QuestionAnswer questionAnswer) throws Exception {
        //数据合法性校验
        if(!StringUtils.lengthCheck(questionAnswer.getQuestion(),0,500)){
            return Collections.emptyList();
        }
        if(questionAnswer.getSuggestNumber()==null){
            questionAnswer.setSuggestNumber(5);
        }
        return KnowledgeDB.suggester.suggest(questionAnswer.getQuestion(),questionAnswer.getSuggestNumber());
    }

    @Override
    public Configuration getConfig() throws Exception {
        return ConfigurationDB.configuration;
    }

    @Override
    public void questionUnderstand(QuestionAnswer questionAnswer) throws Exception {
        //处理问答历史
        QuestionAnswerHistory history = redisUtils.getHistory(Constant.QA_CONTEXT+questionAnswer.getSessionId());
        if(history!=null){
            questionAnswer.setHistory(history);
            //1:推荐问问答编号处理;
            if(StringUtils.regular(StringUtils.INT_REGULAR,questionAnswer.getQuestion())){
                Integer sequence = Integer.parseInt(questionAnswer.getQuestion());
                if(!CollectionUtils.isEmpty(history.getRecommendList())){
                    for(LogQaRecommend recommend:history.getRecommendList()){
                        if(recommend.getSequence()!=sequence){
                            continue;
                        }
                        questionAnswer.setQuestion(recommend.getKnowledgeTitle());
                    }
                }
            }
            //2:上下文处理;
        }else{
            history = new QuestionAnswerHistory();
            history.setSequence(1);
        }
        history.setQuestion(questionAnswer.getQuestion());

        //分词
        List<Term> termList = HanLP.segment(questionAnswer.getQuestion());
        questionAnswer.setSegmentResult(termList);
        history.setSegmentResult(termList.toString());
        //问题分类(指句法上的分类 例:疑问句 以后可以扩展情感识别)

        if(questionAnswer.isDebugMode()){
            //依存句法分析
            CoNLLSentence sentence = HanLP.parseDependency(questionAnswer.getQuestion());
            questionAnswer.setDependencyParserResult(sentence.toString());
            //人名识别
            Segment segment = HanLP.newSegment().enableNameRecognize(true);
            List<Term> chineseNameRecognition = segment.seg(questionAnswer.getQuestion());
            questionAnswer.setChineseNameRecognitionResult(chineseNameRecognition.toString());
            //机构名识别
            segment = HanLP.newSegment().enableOrganizationRecognize(true);
            List<Term> organizationRecognition = segment.seg(questionAnswer.getQuestion());
            questionAnswer.setOrganizationRecognitionResult(organizationRecognition.toString());
            //地名识别
            segment = HanLP.newSegment().enablePlaceRecognize(true);
            List<Term> placeRecognition = segment.seg(questionAnswer.getQuestion());
            questionAnswer.setPlaceRecognitionResult(placeRecognition.toString());
            //URL识别
            List<Term> uRLRecognition = URLTokenizer.segment(questionAnswer.getQuestion());
            questionAnswer.setURLRecognitionResult(uRLRecognition.toString());
            //关键词提取
            List<String> keyWords = HanLP.extractKeyword(questionAnswer.getQuestion(),Constant.MAX_KEYWORDS_NUMBER);
            questionAnswer.setKeyWords(keyWords.toString());
            //新词发现
            List<WordInfo> newWordDiscover = HanLP.extractWords(questionAnswer.getQuestion(),Constant.MAX_NEW_WORD_NUMBER);
            questionAnswer.setNewWordDiscover(newWordDiscover.toString());
            //拼音转汉字
            LinkedList<ResultTerm<Set<String>>> pinYinResult = KnowledgeDB.pinYinSegment.segment(questionAnswer.getQuestion());
            questionAnswer.setPinYinResult(pinYinResult.toString());
        }
        //去除停用词
        CoreStopWordDictionary.apply(termList);
        questionAnswer.setDeleteStopWordsResult(termList);
        history.setDeleteStopWordsResult(termList.toString());
        questionAnswer.setHistory(history);
    }

    @Override
    public void informationRetrieval(QuestionAnswer questionAnswer) throws Exception {
        List<Retrieval> retrievalList = new ArrayList<>();
        //知识库信息检索
        for(Map.Entry<String,Knowledge> entry: KnowledgeDB.knowledgeMap.entrySet()){
            String knowledgeTitle = entry.getValue().getTitle();
            double similarity = 0.00d;
            //完全匹配相似度直接设置为1
            if(questionAnswer.getQuestion().equals(entry.getValue().getTitle())){
                similarity = 1.00d;
                Retrieval retrieval = new Retrieval(entry.getKey(),knowledgeTitle,similarity);
                retrievalList.add(retrieval);
                continue;
            }
            //在匹配单个知识的标题与扩展问时,只取最高的那个相似度
            similarity = StringUtils.getJaroWinklerDistance(questionAnswer.getDeleteStopWordsResult().toString(), entry.getValue().getTermList().toString());
            if(!CollectionUtils.isEmpty(entry.getValue().getExtensionQuestionList())){
                for(ExtensionQuestion extensionQuestion:entry.getValue().getExtensionQuestionList()){
                    double temp = 0.00d;
                    //完全匹配相似度直接设置为1
                    if(questionAnswer.getQuestion().equals(extensionQuestion.getTitle())){
                        similarity = 1.00d;
                        break;
                    }
                    if(CollectionUtils.isEmpty(extensionQuestion.getTermList())){
                        temp = StringUtils.getJaroWinklerDistance(questionAnswer.getDeleteStopWordsResult().toString(), extensionQuestion.getTitle());
                    }else{
                        temp = StringUtils.getJaroWinklerDistance(questionAnswer.getDeleteStopWordsResult().toString(), extensionQuestion.getTermList().toString());
                    }
                    if(temp>similarity){
                        knowledgeTitle = extensionQuestion.getTitle();
                        similarity=temp;
                    }
                }
            }
            //过滤小于系统设置的最小阈值的知识
            if(similarity < ConfigurationDB.configuration.getThresholdLowerLimit()){
                continue;
            }
            Retrieval retrieval = new Retrieval(entry.getKey(),knowledgeTitle,similarity);
            retrievalList.add(retrieval);
        }
        //寒暄库信息检索

        //排序后,取top
        if(!CollectionUtils.isEmpty(retrievalList)){
            Collections.sort(retrievalList, COMPARE_UTILS);
            questionAnswer.setRetrievalList(retrievalList.subList(0,retrievalList.size()>=Constant.MAX_RETRIEVAL_NUMBER?Constant.MAX_RETRIEVAL_NUMBER:retrievalList.size()));
        }
    }

    @Override
    public void answerGenerate(QuestionAnswer questionAnswer) throws Exception {
        QuestionAnswerHistory history = questionAnswer.getHistory();
        int sequence = history.getSequence();
        if(!CollectionUtils.isEmpty(questionAnswer.getRetrievalList())){
            List<Retrieval> retrievalList = questionAnswer.getRetrievalList();
            Retrieval retrieval = retrievalList.get(0);
            //有答案的情况
            hasAnswer(questionAnswer, history, retrievalList, retrieval);
            //有推荐问的情况
            hasRecommend(questionAnswer, history, sequence, retrievalList);
        }else if(questionAnswer.getGreetingRetrieval()!=null){
            //处理寒暄回答
            questionAnswer.setResponseType(Enum.greetingResponse.getValue());
            history.setResponseType(Enum.greetingResponse.getValue());
        }else{
            //处理未知回答
            questionAnswer.setReminders(ConfigurationDB.configuration.getUnknown());
            questionAnswer.setResponseType(Enum.unknowResponse.getValue());
            history.setResponseType(Enum.unknowResponse.getValue());
        }
        //设置问答上下文
        redisUtils.set(Constant.QA_CONTEXT+questionAnswer.getSessionId(),gson.toJson(history),ConfigurationDB.configuration.getTimeout()*60);
    }

    /**
     * 处理有推荐问
     * @param questionAnswer
     * @param history
     * @param sequence
     * @param retrievalList
     */
    private void hasRecommend(QuestionAnswer questionAnswer, QuestionAnswerHistory history, int sequence, List<Retrieval> retrievalList) {
        List<LogQaRecommend> recommendList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(retrievalList)){
            if(retrievalList.size()>ConfigurationDB.configuration.getRecommendQuestionNumber()){
                retrievalList = retrievalList.subList(0,ConfigurationDB.configuration.getRecommendQuestionNumber());
            }
            for(Retrieval retrieval1:retrievalList){
                LogQaRecommend logQaRecommend = new LogQaRecommend();
                logQaRecommend.setId(IdWorker.getInstance().nextId());
                logQaRecommend.setQaId(questionAnswer.getId());
                logQaRecommend.setKnowledgeId(retrieval1.getKnowledge());
                Knowledge knowledge = KnowledgeDB.knowledgeMap.get(retrieval1.getKnowledge());
                logQaRecommend.setKnowledgeTitle(knowledge.getTitle());
                logQaRecommend.setSessionId(questionAnswer.getSessionId());
                logQaRecommend.setSequence(sequence++);
                recommendList.add(logQaRecommend);
            }
            questionAnswer.setRecommendList(recommendList);
            history.setSequence(sequence);
            if(CollectionUtils.isEmpty(history.getRecommendList())){
                history.setRecommendList(recommendList);
            }else{
                history.getRecommendList().addAll(recommendList);
            }
        }
        if(!StringUtils.isEmpty(questionAnswer.getAnswer()) && !CollectionUtils.isEmpty(recommendList)){
            questionAnswer.setReminders(ConfigurationDB.configuration.getHasAnswerAndRecommend());
            questionAnswer.setResponseType(Enum.hasAnswerAndRecommendResponse.getValue());
            history.setResponseType(Enum.hasAnswerAndRecommendResponse.getValue());
        }else if(StringUtils.isEmpty(questionAnswer.getAnswer()) && !CollectionUtils.isEmpty(recommendList)){
            questionAnswer.setReminders(ConfigurationDB.configuration.getHasRecommend());
            questionAnswer.setResponseType(Enum.hasRecommendResponse.getValue());
            history.setResponseType(Enum.hasRecommendResponse.getValue());
        }else if(!StringUtils.isEmpty(questionAnswer.getAnswer()) && CollectionUtils.isEmpty(recommendList)){
            questionAnswer.setResponseType(Enum.hasAnswerResponse.getValue());
            history.setResponseType(Enum.hasAnswerResponse.getValue());
        }
    }

    /**
     * 处理有答案
     * @param questionAnswer
     * @param history
     * @param retrievalList
     * @param retrieval
     */
    private void hasAnswer(QuestionAnswer questionAnswer, QuestionAnswerHistory history, List<Retrieval> retrievalList, Retrieval retrieval) {
        if(retrieval.getSimilarity()> ConfigurationDB.configuration.getThresholdUpperLimit()){
            Knowledge knowledge = KnowledgeDB.knowledgeMap.get(retrieval.getKnowledge());
            List<Answer> answerList = knowledge.getAnswerList();
            for(Answer answer:answerList){
                if(answer.getChannelId()!= Enum.allChannel.getValue() && answer.getChannelId()!=questionAnswer.getChannelId()){
                    continue;
                }
                questionAnswer.setAnswer(answer.getContent());
                history.setAnswer(answer.getContent());
                questionAnswer.setKnowledgeId(knowledge.getId());
                history.setKnowledgeId(knowledge.getId());
                questionAnswer.setClassificationId(knowledge.getClassificationId());
                history.setClassificationId(knowledge.getClassificationId());
            }
            retrievalList.remove(0);
        }
    }
}
