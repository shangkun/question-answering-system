package cn.ken.question.answering.system.service.log;

import cn.ken.question.answering.system.model.qa.QuestionAnswer;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/30 <br/>
 * what: LogService <br/>
 */
public interface LogService {
    /**
     * 添加一条问答记录
     * @param questionAnswer
     * @return
     */
    void add(QuestionAnswer questionAnswer) throws Exception;

    /**
     * 按照时间区域查询数量
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    int count(String startTime,String endTime) throws Exception;

    /**
     * 批量添加
     * @param questionAnswerList
     * @return
     */
    void batchAdd(List<QuestionAnswer> questionAnswerList) throws Exception;
}
