package cn.ken.question.answering.system.service.log;

import cn.ken.question.answering.system.model.log.LogQa;
import cn.ken.question.answering.system.model.log.LogQaRequest;
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
     * 根据属性值计数
     * @param request
     * @return
     * @throws Exception
     */
    int countByAttribute(LogQaRequest request) throws Exception;

    /**
     * 获取知识点排名数据
     * @param request
     * @return
     * @throws Exception
     */
    List<LogQa> getKnowledegRanking(LogQaRequest request) throws Exception;

    /**
     * 批量添加
     * @param questionAnswerList
     * @return
     */
    void batchAdd(List<QuestionAnswer> questionAnswerList) throws Exception;
}
