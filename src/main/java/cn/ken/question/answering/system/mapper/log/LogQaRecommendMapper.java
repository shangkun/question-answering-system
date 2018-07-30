package cn.ken.question.answering.system.mapper.log;

import cn.ken.question.answering.system.model.log.LogQaRecommend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogQaRecommendMapper{

    /**
     * 通过id查询一条数据
     * @param logId
     * @return
     */
    List<LogQaRecommend> selectByLogId(@Param("logId")String logId);

    /**
     * 批量添加
     * @param list
     * @return
     */
    int batchInsert(List<LogQaRecommend> list);
}