package cn.ken.question.answering.system.service.statistics;

import cn.ken.question.answering.system.model.statistics.InfoStatistics;
import cn.ken.question.answering.system.model.statistics.InfoStatisticsRequest;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/30 <br/>
 * what: InfoStatisticsService <br/>
 */
public interface InfoStatisticsService {

    List<InfoStatistics> get() throws Exception;

    InfoStatistics statistics(InfoStatisticsRequest request) throws Exception;
}
