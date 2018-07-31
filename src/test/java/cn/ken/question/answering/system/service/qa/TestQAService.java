package cn.ken.question.answering.system.service.qa;

import cn.ken.question.answering.system.JUnit4BaseConfig;
import cn.ken.question.answering.system.model.qa.QuestionAnswer;
import cn.ken.question.answering.system.utils.IdWorker;
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
        questionAnswer.setChannelId(cn.ken.question.answering.system.common.Enum.webChannel.getValue());
        questionAnswer.setQuestion("系统的关闭");
        QuestionAnswer questionAnswer1  = qaService.qa(questionAnswer);
        questionAnswer1.getAnswer();
    }
}
