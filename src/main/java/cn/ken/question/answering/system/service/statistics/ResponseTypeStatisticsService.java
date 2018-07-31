package cn.ken.question.answering.system.service.statistics;

import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.model.statistics.ResponseTypeStatistics;
import cn.ken.question.answering.system.model.statistics.ResponseTypeStatisticsRequest;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/30 <br/>
 * what: KnowledgeRankingService <br/>
 */
public interface ResponseTypeStatisticsService {
    /**
     * 查询历史统计数据
     * @param request
     * @return
     * @throws Exception
     */
    List<ResponseTypeStatistics> get(ResponseTypeStatisticsRequest request) throws Exception;

    /**
     * 历史数据汇总
     * @param request
     * @return
     * @throws Exception
     */
    ResponseTypeStatistics sum(ResponseTypeStatisticsRequest request) throws Exception;
    /**
     * 统计
     * @param request
     * @return
     * @throws Exception
     */
    ResponseTypeStatistics statistics(ResponseTypeStatisticsRequest request) throws Exception;
}
