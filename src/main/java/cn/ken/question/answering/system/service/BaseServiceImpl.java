package cn.ken.question.answering.system.service;

import cn.ken.question.answering.system.mapper.log.LogQaMapper;
import cn.ken.question.answering.system.mapper.log.LogQaRecommendMapper;
import cn.ken.question.answering.system.model.log.LogQa;
import cn.ken.question.answering.system.model.log.LogQaRecommend;
import cn.ken.question.answering.system.model.qa.QuestionAnswer;
import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.common.Constant;
import cn.ken.question.answering.system.mapper.knowledge.GreetingAnswerMapper;
import cn.ken.question.answering.system.mapper.knowledge.GreetingExtensionQuestionMapper;
import cn.ken.question.answering.system.mapper.knowledge.GreetingMapper;
import cn.ken.question.answering.system.model.knowledge.Greeting;
import cn.ken.question.answering.system.model.knowledge.GreetingAnswer;
import cn.ken.question.answering.system.model.knowledge.GreetingExtensionQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/30 <br/>
 * what: BaseServiceImpl <br/>
 */
@Service
public class BaseServiceImpl extends Base implements BaseService{
    @Autowired
    private GreetingMapper greetingMapper;
    @Autowired
    private GreetingExtensionQuestionMapper greetingExtensionQuestionMapper;
    @Autowired
    private GreetingAnswerMapper greetingAnswerMapper;
    @Autowired
    private LogQaMapper logQaMapper;
    @Autowired
    private LogQaRecommendMapper logQaRecommendMapper;

    @Override
    public int batchAddGreeting(List<Greeting> greetingList) {
        if(CollectionUtils.isEmpty(greetingList)){
            return 0;
        }
        int result=0;
        if(greetingList.size()> Constant.BATCH_NUMBER){
            int count = greetingList.size() % Constant.BATCH_NUMBER==0?greetingList.size() /
                    Constant.BATCH_NUMBER :
                    (greetingList.size()/ Constant.BATCH_NUMBER)+1;
            for(int i=0;i<count;i++){
                int start = i * Constant.BATCH_NUMBER,end = i==count-1?greetingList.size():(i+1) * Constant.BATCH_NUMBER;
                List<Greeting> temp = greetingList.subList(start,end);
                result = greetingMapper.batchInsert(temp);
                result += result;
            }
        }else{
            result = greetingMapper.batchInsert(greetingList);
        }
        return result;
    }

    @Override
    public int batchAddGreetingExtensionQuestion(List<GreetingExtensionQuestion> extensionQuestionList) {
        if(CollectionUtils.isEmpty(extensionQuestionList)){
            return 0;
        }
        int result=0;
        if(extensionQuestionList.size()> Constant.BATCH_NUMBER){
            int count = extensionQuestionList.size() % Constant.BATCH_NUMBER==0?extensionQuestionList.size() /
                    Constant.BATCH_NUMBER :
                    (extensionQuestionList.size()/ Constant.BATCH_NUMBER)+1;
            for(int i=0;i<count;i++){
                int start = i * Constant.BATCH_NUMBER,end = i==count-1?extensionQuestionList.size():(i+1) * Constant.BATCH_NUMBER;
                List<GreetingExtensionQuestion> temp = extensionQuestionList.subList(start,end);
                result = greetingExtensionQuestionMapper.batchInsert(temp);
                result += result;
            }
        }else{
            result = greetingExtensionQuestionMapper.batchInsert(extensionQuestionList);
        }
        return result;
    }

    @Override
    public int batchAddGreetingAnswer(List<GreetingAnswer> greetingAnswerList) {
        if(CollectionUtils.isEmpty(greetingAnswerList)){
            return 0;
        }
        int result=0;
        if(greetingAnswerList.size()> Constant.BATCH_NUMBER){
            int count = greetingAnswerList.size() % Constant.BATCH_NUMBER==0?greetingAnswerList.size() /
                    Constant.BATCH_NUMBER :
                    (greetingAnswerList.size()/ Constant.BATCH_NUMBER)+1;
            for(int i=0;i<count;i++){
                int start = i * Constant.BATCH_NUMBER,end = i==count-1?greetingAnswerList.size():(i+1) * Constant.BATCH_NUMBER;
                List<GreetingAnswer> temp = greetingAnswerList.subList(start,end);
                result = greetingAnswerMapper.batchInsert(temp);
                result += result;
            }
        }else{
            result = greetingAnswerMapper.batchInsert(greetingAnswerList);
        }
        return result;
    }

    @Override
    public int batchAddLogQa(List<QuestionAnswer> logQaList) throws Exception {
        if(CollectionUtils.isEmpty(logQaList)){
            return 0;
        }
        int result=0;
        if(logQaList.size()> Constant.BATCH_NUMBER){
            int count = logQaList.size() % Constant.BATCH_NUMBER==0?logQaList.size() /
                    Constant.BATCH_NUMBER :
                    (logQaList.size()/ Constant.BATCH_NUMBER)+1;
            for(int i=0;i<count;i++){
                int start = i * Constant.BATCH_NUMBER,end = i==count-1?logQaList.size():(i+1) * Constant.BATCH_NUMBER;
                List<QuestionAnswer> temp = logQaList.subList(start,end);
                result = logQaMapper.batchInsert(temp);
                result += result;
            }
        }else{
            result = logQaMapper.batchInsert(logQaList);
        }
        return result;
    }

    @Override
    public int batchAddLogQaRecommend(List<LogQaRecommend> logQaRecommendList) throws Exception {
        if(CollectionUtils.isEmpty(logQaRecommendList)){
            return 0;
        }
        int result=0;
        if(logQaRecommendList.size()> Constant.BATCH_NUMBER){
            int count = logQaRecommendList.size() % Constant.BATCH_NUMBER==0?logQaRecommendList.size() /
                    Constant.BATCH_NUMBER :
                    (logQaRecommendList.size()/ Constant.BATCH_NUMBER)+1;
            for(int i=0;i<count;i++){
                int start = i * Constant.BATCH_NUMBER,end = i==count-1?logQaRecommendList.size():(i+1) * Constant.BATCH_NUMBER;
                List<LogQaRecommend> temp = logQaRecommendList.subList(start,end);
                result = logQaRecommendMapper.batchInsert(temp);
                result += result;
            }
        }else{
            result = logQaRecommendMapper.batchInsert(logQaRecommendList);
        }
        return result;
    }
}
