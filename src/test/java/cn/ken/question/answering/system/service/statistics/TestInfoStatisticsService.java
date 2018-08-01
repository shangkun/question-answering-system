package cn.ken.question.answering.system.service.statistics;

import cn.ken.question.answering.system.JUnit4BaseConfig;
import cn.ken.question.answering.system.mapper.statistics.InfoStatisticsMapper;
import cn.ken.question.answering.system.model.statistics.InfoStatistics;
import cn.ken.question.answering.system.model.statistics.InfoStatisticsRequest;
import cn.ken.question.answering.system.utils.TimeUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/8/1 <br/>
 * what: TestInfoStatisticsService <br/>
 */
public class TestInfoStatisticsService extends JUnit4BaseConfig{
    @Autowired
    private InfoStatisticsService infoStatisticsService;
    @Autowired
    private InfoStatisticsMapper infoStatisticsMapper;
    @Test
    public void task() throws Exception{
        List<String> timeList = TimeUtils.getTimeSlot("2018-07-01", "2018-08-01");
        for(String time:timeList){
            InfoStatisticsRequest request = new InfoStatisticsRequest(time+TimeUtils.START_HOUR,time+TimeUtils.END_HOUR);
            request.setTime(time);
            InfoStatistics infoStatistics = infoStatisticsService.statistics(request);
            if(infoStatistics.getGreeting()==0 &&
                    infoStatistics.getClassification()==0 &&
                    infoStatistics.getKnowledge()==0 &&
                    infoStatistics.getLexicon()==0 &&
                    infoStatistics.getQa()==0){
                continue;
            }
            infoStatisticsMapper.insert(infoStatistics);
        }
    }
}
