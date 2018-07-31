package cn.ken.question.answering.system.service.statistics;

import cn.ken.question.answering.system.JUnit4BaseConfig;
import cn.ken.question.answering.system.mapper.statistics.ResponseTypeStatisticsMapper;
import cn.ken.question.answering.system.model.statistics.ResponseTypeStatistics;
import cn.ken.question.answering.system.model.statistics.ResponseTypeStatisticsRequest;
import cn.ken.question.answering.system.utils.TimeUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


/**
 * author: shangkun <br/>
 * date: 2018/7/27 <br/>
 * what: TestResponseTypeStatisticsService <br/>
 */
public class TestResponseTypeStatisticsService extends JUnit4BaseConfig {
    @Autowired
    private ResponseTypeStatisticsService responseTypeStatisticsService;
    @Autowired
    private ResponseTypeStatisticsMapper responseTypeStatisticsMapper;

    @Test
    public void test() throws Exception{
        Map<String,String> map = TimeUtils.timeCondition(3,true);
        String startTime = map.get(TimeUtils.START_TIME);
        String endTime = map.get(TimeUtils.END_TIME);
        ResponseTypeStatisticsRequest request = new ResponseTypeStatisticsRequest(startTime,endTime);
        ResponseTypeStatistics statistics = responseTypeStatisticsService.statistics(request);
        statistics.setTime(map.get(TimeUtils.TIME));
        responseTypeStatisticsMapper.insert(statistics);

        map = TimeUtils.timeCondition(3,false);
        startTime = map.get(TimeUtils.START_TIME);
        endTime = map.get(TimeUtils.END_TIME);
        request = new ResponseTypeStatisticsRequest(startTime,endTime);
        ResponseTypeStatistics responseTypeStatistics = responseTypeStatisticsService.get(request);
        responseTypeStatistics.getGreeting();
    }
}

