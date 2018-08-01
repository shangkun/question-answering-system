package cn.ken.question.answering.system.memorydb;

import cn.ken.question.answering.system.common.Constant;
import cn.ken.question.answering.system.model.knowledge.*;
import cn.ken.question.answering.system.utils.Base;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dictionary.StringDictionary;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.Other.CommonAhoCorasickDoubleArrayTrieSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.suggest.Suggester;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author: shangkun <br/>
 * date: 2018/7/26 <br/>
 * what: KnowledgeDB <br/>
 */
public class KnowledgeDB extends Base {
    /**
     * 知识
     */
    public static Map<String,Knowledge> knowledgeMap = new ConcurrentHashMap<>();
    /**
     * 寒暄知识
     */
    public static Map<String,Greeting> greetingMap = new ConcurrentHashMap<>();
    /**
     * 推荐器
     */
    public static Suggester suggester = new Suggester();
    /**
     * 拼音
     */
    public static TreeMap<String, Set<String>> map = new TreeMap<>();
    /**
     * 拼音分词器
     */
    public static  CommonAhoCorasickDoubleArrayTrieSegment<Set<String>> pinYinSegment;

    /**
     * 初始化拼音
     */
    public static void initPinYinMap(){
        StringDictionary dictionary = new StringDictionary("=");
        dictionary.load(HanLP.Config.PinyinDictionaryPath);
        for (Map.Entry<String, String> entry : dictionary.entrySet())
        {
            String pinyins = entry.getValue().replaceAll("[\\d,]", "");
            Set<String> words = map.get(pinyins);
            if (words == null)
            {
                words = new TreeSet<>();
                map.put(pinyins, words);
            }
            words.add(entry.getKey());
        }
        pinYinSegment = new CommonAhoCorasickDoubleArrayTrieSegment<>(map);
    }

    /**
     * 初始化知识
     * @param knowledgeList
     */
    public static void initKnowledge(List<Knowledge> knowledgeList,List<ExtensionQuestion> extensionQuestionList,List<Answer> answerList){
        //清理所有推荐文本
        suggester.removeAllSentences();

        for(Knowledge knowledge:knowledgeList){
            try {
                List<Term> termListTemp = new ArrayList<>();
                List<Term> termList = HanLP.segment(knowledge.getTitle());
                termListTemp = termList;
                CoreStopWordDictionary.apply(termList);
                knowledge.setTermList(termList.size()==0?termListTemp:termList);
                suggester.addSentence(knowledge.getTitle());
                knowledge.setExtensionQuestionList(new ArrayList<ExtensionQuestion>());
                knowledge.setAnswerList(new ArrayList<Answer>());

                knowledgeMap.put(knowledge.getId(),knowledge);
            } catch (Exception e) {
                continue;
            }
        }
        for(ExtensionQuestion extensionQuestion:extensionQuestionList){
            try {
                List<Term> termListTemp = new ArrayList<>();
                Knowledge knowledge = knowledgeMap.get(extensionQuestion.getKnowledgeId());
                List<Term> termList1 = HanLP.segment(extensionQuestion.getTitle());
                termListTemp = termList1;
                CoreStopWordDictionary.apply(termList1);
                extensionQuestion.setTermList(termList1.size()==0?termListTemp:termList1);
                suggester.addSentence(extensionQuestion.getTitle());
                knowledge.getExtensionQuestionList().add(extensionQuestion);
            }catch (Exception e){
                continue;
            }
        }
        for(Answer answer:answerList){
            try {
                Knowledge knowledge = knowledgeMap.get(answer.getKnowledgeId());
                List<String> summary = HanLP.extractSummary(answer.getContent(), Constant.MAX_SUMMARY_NUMBER);
                answer.setSummaryList(summary);
                knowledge.getAnswerList().add(answer);
            }catch (Exception e){
                knowledgeMap.remove(answer.getKnowledgeId());
                continue;
            }
        }
    }

    /**
     * 初始化寒暄知识
     * @param greetingList
     * @param greetingExtensionQuestionList
     * @param greetingAnswerList
     */
    public static void initGreeting(List<Greeting> greetingList,List<GreetingExtensionQuestion> greetingExtensionQuestionList,List<GreetingAnswer> greetingAnswerList){
        for(Greeting greeting:greetingList){
            try {
                List<Term> termList = HanLP.segment(greeting.getTitle());
                greeting.setTermList(termList);
                greeting.setGreetingExtensionQuestionList(new ArrayList<GreetingExtensionQuestion>());
                greeting.setGreetingAnswerList(new ArrayList<GreetingAnswer>());
                greetingMap.put(greeting.getId(),greeting);
            } catch (Exception e) {
                continue;
            }
        }
        for(GreetingExtensionQuestion extensionQuestion:greetingExtensionQuestionList){
            try {
                Greeting greeting = greetingMap.get(extensionQuestion.getGreetingId());
                List<Term> termList1 = HanLP.segment(extensionQuestion.getTitle());
                extensionQuestion.setTermList(termList1);
                greeting.getGreetingExtensionQuestionList().add(extensionQuestion);
            }catch (Exception e){
                continue;
            }
        }
        for(GreetingAnswer greetingAnswer:greetingAnswerList){
            try {
                Greeting greeting = greetingMap.get(greetingAnswer.getGreetingId());
                greeting.getGreetingAnswerList().add(greetingAnswer);
            }catch (Exception e){
                greetingMap.remove(greetingAnswer.getGreetingId());
                continue;
            }
        }
    }

    public static void initSingleKnowledge(Knowledge knowledge){
        try {
            if(CollectionUtils.isEmpty(knowledge.getAnswerList())){
                return;
            }
            suggester.addSentence(knowledge.getTitle());
            List<Term> termList = HanLP.segment(knowledge.getTitle());
            CoreStopWordDictionary.apply(termList);
            knowledge.setTermList(termList);
            if(CollectionUtils.isEmpty(knowledge.getExtensionQuestionList())){
                for(ExtensionQuestion extensionQuestion:knowledge.getExtensionQuestionList()){
                    List<Term> termList1 = HanLP.segment(knowledge.getTitle());
                    CoreStopWordDictionary.apply(termList1);
                    extensionQuestion.setTermList(termList1);
                    suggester.addSentence(extensionQuestion.getTitle());
                }
            }
            List<Answer> answerList = knowledge.getAnswerList();
            for(Answer answer:answerList){
                List<String> summary = HanLP.extractSummary(answer.getContent(), Constant.MAX_SUMMARY_NUMBER);
                answer.setSummaryList(summary);
            }
            knowledgeMap.put(knowledge.getId(),knowledge);
        } catch (Exception e) {
            return;
        }
    }

    public static void initSingleGreeting(Greeting greeting){
        try {
            if(CollectionUtils.isEmpty(greeting.getGreetingAnswerList())){
                return;
            }
            List<Term> termList = HanLP.segment(greeting.getTitle());
            CoreStopWordDictionary.apply(termList);
            greeting.setTermList(termList);
            if(CollectionUtils.isEmpty(greeting.getGreetingExtensionQuestionList())){
                for(GreetingExtensionQuestion extensionQuestion:greeting.getGreetingExtensionQuestionList()){
                    List<Term> termList1 = HanLP.segment(greeting.getTitle());
                    CoreStopWordDictionary.apply(termList1);
                    extensionQuestion.setTermList(termList1);
                }
            }
            greetingMap.put(greeting.getId(),greeting);
        } catch (Exception e) {
            return;
        }
    }
}
