package cn.ken.question.answering.system.service.statistics;

import cn.ken.question.answering.system.JUnit4BaseConfig;
import cn.ken.question.answering.system.common.Enum;
import cn.ken.question.answering.system.mapper.statistics.KnowledegRankingMapper;
import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.model.statistics.KnowledgeRanking;
import cn.ken.question.answering.system.model.statistics.KnowledgeRankingRequest;
import cn.ken.question.answering.system.utils.TimeUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;


/**
 * author: shangkun <br/>
 * date: 2018/7/27 <br/>
 * what: TestKnowledgeRankingService <br/>
 */
public class TestKnowledgeRankingService extends JUnit4BaseConfig {
    @Autowired
    private KnowledgeRankingService knowledgeRankingService;
    @Autowired
    private KnowledegRankingMapper knowledegRankingMapper;

    @Test
    public void test() throws Exception{
        Map<String,String> map = TimeUtils.timeCondition(3,true);
        String startTime = map.get(TimeUtils.START_TIME);
        String endTime = map.get(TimeUtils.END_TIME);
        KnowledgeRankingRequest request = new KnowledgeRankingRequest(startTime,endTime);
        request.setTime(map.get(TimeUtils.TIME));
        List<KnowledgeRanking> knowledgeRankingList = knowledgeRankingService.statistics(request);
        if(CollectionUtils.isEmpty(knowledgeRankingList)){
            return;
        }
        knowledegRankingMapper.batchInsert(knowledgeRankingList);

        List<KnowledgeRanking> knowledgeRankingList1 = knowledgeRankingService.getHotKnowledge(Enum.webChannel.getValue());
        knowledgeRankingList1.size();

        PageData pageData = knowledgeRankingService.get(request);
        pageData.getTotal();
    }

    @Test
    public void task() throws Exception{
        List<String> timeList = TimeUtils.getTimeSlot("2018-07-01","2018-08-01");
        for(String time:timeList){
            KnowledgeRankingRequest request = new KnowledgeRankingRequest(time+TimeUtils.START_HOUR,time+TimeUtils.END_HOUR);
            request.setTime(time);
            List<KnowledgeRanking> knowledgeRankingList = knowledgeRankingService.statistics(request);
            if(CollectionUtils.isEmpty(knowledgeRankingList)){
                continue;
            }
            knowledegRankingMapper.batchInsert(knowledgeRankingList);
        }
    }
}

