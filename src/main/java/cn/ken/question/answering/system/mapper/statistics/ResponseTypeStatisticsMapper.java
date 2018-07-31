package cn.ken.question.answering.system.mapper.statistics;

import cn.ken.question.answering.system.model.statistics.ResponseTypeStatistics;

public interface ResponseTypeStatisticsMapper {
    /**
     * 添加一条数据
     * @param model
     * @return
     */
    int insert(ResponseTypeStatistics model);

    /**
     * 更新一条数据
     * @param model
     * @return
     */
    int update(ResponseTypeStatistics model);

    /**
     * 根据时间区域查询
     * @param startTime
     * @param endTime
     * @return
     */
    ResponseTypeStatistics sumByTime(String startTime, String endTime);

    /**
     * 通过单个时间获取
     * @param time
     * @return
     */
    ResponseTypeStatistics getByTime(String time);
}