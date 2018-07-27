package cn.ken.questionansweringsystem.service;

import cn.ken.questionansweringsystem.JUnit4BaseConfig;
import cn.ken.questionansweringsystem.common.*;
import cn.ken.questionansweringsystem.common.Enum;
import cn.ken.questionansweringsystem.model.knowledge.Answer;
import cn.ken.questionansweringsystem.model.knowledge.KnowledgeRequest;
import cn.ken.questionansweringsystem.model.response.PageData;
import cn.ken.questionansweringsystem.service.knowledge.KnowledgeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
        KnowledgeRequest knowledgeRequest = new KnowledgeRequest();
        knowledgeRequest.setTitle("fdsfjhudsifhds");
        knowledgeRequest.setModifierId("fdsafds");
        knowledgeRequest.setClassificationId("540256092177825792");
        knowledgeRequest.setStatus(1);
        List<String> extensionQuestionList = new ArrayList<>();
        extensionQuestionList.add("fdsfds");
        List<Answer> answerList = new ArrayList<>();
        Answer answer = new Answer();
        answer.setContent("fghjidslhfdsoj");
        answer.setChannelId(Enum.allChannel.getValue());
        answerList.add(answer);
        knowledgeRequest.setExtensionQuestionTitleList(extensionQuestionList);
        knowledgeRequest.setAnswerList(answerList);
        knowledgeService.add(knowledgeRequest);
        PageData pageData = knowledgeService.get(knowledgeRequest);
        pageData.getData();
    }
}

