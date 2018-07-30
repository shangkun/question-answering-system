package cn.ken.question.answering.system.service.qa;

import cn.ken.question.answering.system.model.configuration.Configuration;
import cn.ken.question.answering.system.model.qa.QuestionAnswer;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/28 <br/>
 * what: BaseService <br/>
 */
public interface QAService {

    /**
     * 问答数据预处理
     * @param questionAnswer
     * @return
     * @throws Exception
     */
    QuestionAnswer qa(QuestionAnswer questionAnswer) throws Exception;

    /**
     * 问题推荐
     * @param questionAnswer
     * @return
     * @throws Exception
     */
    List<String> suggest(QuestionAnswer questionAnswer) throws Exception;

    /**
     * 获取问答配置
     * @return
     * @throws Exception
     */
    Configuration getConfig() throws Exception;

    /**
     * 问题理解
     * @param questionAnswer
     */
    void questionUnderstand(QuestionAnswer questionAnswer) throws Exception;

    /**
     * 信息检索
     * @param questionAnswer
     */
    void informationRetrieval(QuestionAnswer questionAnswer) throws Exception;

    /**
     * 答案生成
     * @param questionAnswer
     */
    void answerGenerate(QuestionAnswer questionAnswer) throws Exception;
}
