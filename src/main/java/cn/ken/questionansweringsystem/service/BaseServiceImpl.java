package cn.ken.questionansweringsystem.service;

import cn.ken.questionansweringsystem.common.Constant;
import cn.ken.questionansweringsystem.mapper.knowledge.GreetingAnswerMapper;
import cn.ken.questionansweringsystem.mapper.knowledge.GreetingExtensionQuestionMapper;
import cn.ken.questionansweringsystem.mapper.knowledge.GreetingMapper;
import cn.ken.questionansweringsystem.model.knowledge.Greeting;
import cn.ken.questionansweringsystem.model.knowledge.GreetingAnswer;
import cn.ken.questionansweringsystem.model.knowledge.GreetingExtensionQuestion;
import cn.ken.questionansweringsystem.utils.Base;
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
}
