package cn.ken.question.answering.system.mapper.log;

import cn.ken.question.answering.system.model.log.LogQa;
import cn.ken.question.answering.system.model.log.LogQaRequest;
import cn.ken.question.answering.system.model.qa.QuestionAnswer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogQaMapper{

    /**
     * 添加一条数据
     * @param questionAnswer
     * @return
     */
    int insert(QuestionAnswer questionAnswer);

    /**
     * 统计开始结束时间
     * @param startTime
     * @param endTime
     * @return
     */
    int count(String startTime,String endTime);

    /**
     * 根据属性计算总数
     * @param request
     * @return
     */
    int countByAttribute(LogQaRequest request);

    /**
     * 通过id查询一条数据
     * @param id
     * @return
     */
    LogQa selectById(@Param("id")String id);

    /**
     * 批量添加
     * @param list
     * @return
     */
    int batchInsert(List<QuestionAnswer> list);

    /**
     * 获取知识点排名数据
     * @param request
     * @return
     */
    List<LogQa> getKnowledgeRanking(LogQaRequest request);
}