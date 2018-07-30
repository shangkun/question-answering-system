package cn.ken.question.answering.system.service;

import cn.ken.question.answering.system.JUnit4BaseConfig;
import cn.ken.question.answering.system.model.knowledge.Answer;
import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.utils.TextFileReader;
import cn.ken.question.answering.system.model.knowledge.KnowledgeRequest;
import cn.ken.question.answering.system.service.knowledge.KnowledgeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: shangkun <br/>
 * date: 2018/7/27 <br/>
 * what: 知识测试 <br/>
 */
public class TestKnowledgeService extends JUnit4BaseConfig {
    @Autowired
    private KnowledgeService knowledgeService;

    @Test
    public void test() throws Exception{
        Map<String,String> map = new HashMap<>();
        for(int i=0;i<=12;i++){
            TextFileReader.readFileByLines(map, "D:\\work\\毕业设计资料\\10wwhy\\00" + i + ".txt");
        }
        for (Map.Entry<String,String> entry:map.entrySet()){
            KnowledgeRequest knowledgeRequest = new KnowledgeRequest();
            knowledgeRequest.setTitle(entry.getKey());
            knowledgeRequest.setModifierId("538559372305891328");
            knowledgeRequest.setClassificationId("540294769230741504");
            knowledgeRequest.setStatus(1);
            List<Answer> answerList = new ArrayList<>();
            Answer answer = new Answer();
            answer.setContent(entry.getValue());
            answer.setChannelId(cn.ken.question.answering.system.common.Enum.allChannel.getValue());
            answerList.add(answer);
            knowledgeRequest.setAnswerList(answerList);
            knowledgeService.add(knowledgeRequest);
        }
        KnowledgeRequest knowledgeRequest = new KnowledgeRequest();
        knowledgeRequest.setIndex(0);
        knowledgeRequest.setPageSize(10);
        PageData pageData = knowledgeService.get(knowledgeRequest);
        pageData.getData();
    }
}

