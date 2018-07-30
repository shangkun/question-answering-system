package cn.ken.question.answering.system.mapper.statistics;

import cn.ken.question.answering.system.model.statistics.InfoStatistics;

public interface InfoStatisticsMapper{
    /**
     * 添加一条数据
     * @param model
     * @return
     */
    int insert(InfoStatistics model);

    /**
     * 更新一条数据
     * @param model
     * @return
     */
    int update(InfoStatistics model);

    /**
     * 根据时间区域查询
     * @param startTime
     * @param endTime
     * @return
     */
    InfoStatistics selectByTime(String startTime,String endTime);
}