package cn.ken.questionansweringsystem.service;

import cn.ken.questionansweringsystem.JUnit4BaseConfig;
import cn.ken.questionansweringsystem.common.Enum;
import cn.ken.questionansweringsystem.model.qa.QuestionAnswer;
import cn.ken.questionansweringsystem.service.qa.QAService;
import cn.ken.questionansweringsystem.utils.IdWorker;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
        questionAnswer.setChannelId(Enum.webChannel.getValue());
        questionAnswer.setQuestion("系统的关闭");
        QuestionAnswer questionAnswer1  = qaService.qa(questionAnswer);
        questionAnswer1.getAnswer();
    }
}
