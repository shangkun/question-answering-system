package cn.ken.question.answering.system.mapper.log;

import cn.ken.question.answering.system.model.log.LogQaEvaluate;

public interface LogQaEvaluateMapper{

    /**
     * 添加一条数据
     * @param logQaEvaluate
     * @return
     */
    int insert(LogQaEvaluate logQaEvaluate);
}