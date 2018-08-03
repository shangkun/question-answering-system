package cn.ken.question.answering.system.service.qa;

import cn.ken.question.answering.system.JUnit4BaseConfig;
import cn.ken.question.answering.system.common.*;
import cn.ken.question.answering.system.common.Enum;
import cn.ken.question.answering.system.model.qa.QuestionAnswer;
import cn.ken.question.answering.system.utils.IdWorker;
import cn.ken.question.answering.system.utils.TextFileReader;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: shangkun <br/>
 * date: 2018/7/27 <br/>
 * what: 问答测试 <br/>
 */
public class TestQAService extends JUnit4BaseConfig {
    @Autowired
    private QAService qaService;
    @Test
    public void suggest() throws Exception{
        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setQuestion("关闭");
        List<String> list = qaService.suggest(questionAnswer);
        list.size();
    }

    @Test
    public void qa() throws Exception{
        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setDebugMode(true);
        questionAnswer.setUserId(IdWorker.getInstance().nextId());
        questionAnswer.setChannelId(cn.ken.question.answering.system.common.Enum.webChannel.getValue());
        questionAnswer.setQuestion("系统的关闭");
        QuestionAnswer questionAnswer1 = qaService.qa(questionAnswer);
        questionAnswer1.getAnswer();
    }
    @Test
    public void qaAccuracyRate() throws Exception{
        Map<String,String> map = new HashMap<>();
        TextFileReader.readByLines(map,"C:\\Users\\shangkun\\Desktop\\knowledge.txt");
        int answered = 0;
        for(Map.Entry<String,String> entry:map.entrySet()){
            QuestionAnswer questionAnswer = new QuestionAnswer();
            questionAnswer.setUserId(IdWorker.getInstance().nextId());
            questionAnswer.setChannelId(cn.ken.question.answering.system.common.Enum.webChannel.getValue());
            questionAnswer.setQuestion(entry.getValue());
            QuestionAnswer questionAnswer1 = qaService.qa(questionAnswer);
            if(questionAnswer1.getResponseType()== Enum.hasAnswerResponse.getValue() ||
                    questionAnswer1.getResponseType()==Enum.hasAnswerAndRecommendResponse.getValue() ||
                    questionAnswer1.getResponseType()==Enum.hasRecommendResponse.getValue()){
                answered++;
            }else{
                System.out.println(entry.getKey()+":"+questionAnswer1.getQuestion()+":"+questionAnswer1.getResponseType());
            }
        }
        double accuraryRate = (double)answered/(double)map.size();
        System.out.println(accuraryRate);
    }
}
