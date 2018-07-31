package cn.ken.question.answering.system.service.statistics;

import cn.ken.question.answering.system.common.Enum;
import cn.ken.question.answering.system.mapper.statistics.ResponseTypeStatisticsMapper;
import cn.ken.question.answering.system.model.log.LogQaRequest;
import cn.ken.question.answering.system.model.statistics.ResponseTypeStatistics;
import cn.ken.question.answering.system.model.statistics.ResponseTypeStatisticsRequest;
import cn.ken.question.answering.system.service.log.LogService;
import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * author: shangkun <br/>
 * date: 2018/7/31 <br/>
 * what: ResponseTypeStatisticsServiceImpl <br/>
 */
@Service
public class ResponseTypeStatisticsServiceImpl extends Base implements ResponseTypeStatisticsService{
    @Autowired
    private LogService logService;
    @Autowired
    private ResponseTypeStatisticsMapper responseTypeStatisticsMapper;

    @Override
    public List<ResponseTypeStatistics> get(ResponseTypeStatisticsRequest request) throws Exception {
        List<ResponseTypeStatistics> responseTypeStatisticsList = new ArrayList<>();
        Set<String> stringSet = TimeUtils.getTimeSlot(request.getStartTime(),request.getEndTime());
        if(CollectionUtils.isEmpty(stringSet)){
            return responseTypeStatisticsList;
        }
        for(String time:stringSet){
            ResponseTypeStatistics responseTypeStatistics = responseTypeStatisticsMapper.getByTime(time);
            if(responseTypeStatistics==null){
                continue;
            }
            responseTypeStatisticsList.add(responseTypeStatistics);
        }
        return responseTypeStatisticsList;
    }

    @Scheduled(cron="0 0 3 * * ?")
    public void statisticsTask() throws Exception{
        Map<String,String> map = TimeUtils.timeCondition(3,true);
        String startTime = map.get(TimeUtils.START_TIME);
        String endTime = map.get(TimeUtils.END_TIME);
        ResponseTypeStatisticsRequest request = new ResponseTypeStatisticsRequest(startTime,endTime);
        ResponseTypeStatistics statistics = statistics(request);
        statistics.setTime(map.get(TimeUtils.TIME));
        responseTypeStatisticsMapper.insert(statistics);
    }

    @Override
    public ResponseTypeStatistics statistics(ResponseTypeStatisticsRequest request) throws Exception {
        ResponseTypeStatistics statistics = new ResponseTypeStatisticsRequest();
        LogQaRequest logQaRequest = new LogQaRequest(request.getStartTime(),request.getEndTime());
        //有答案有推荐
        logQaRequest.setResponseType(Enum.hasAnswerAndRecommendResponse.getValue());
        statistics.setHasAnswerAndRecommend(logService.countByAttribute(logQaRequest));
        //只有答案
        logQaRequest.setResponseType(Enum.hasAnswerResponse.getValue());
        statistics.setHasAnswer(logService.countByAttribute(logQaRequest));
        //只有推荐
        logQaRequest.setResponseType(Enum.hasRecommendResponse.getValue());
        statistics.setHasRecommend(logService.countByAttribute(logQaRequest));
        //寒暄问答
        logQaRequest.setResponseType(Enum.greetingResponse.getValue());
        statistics.setGreeting(logService.countByAttribute(logQaRequest));
        //未知问答
        logQaRequest.setResponseType(Enum.unknowResponse.getValue());
        statistics.setUnknown(logService.countByAttribute(logQaRequest));
        //其他类型问答
        logQaRequest.setResponseType(Enum.othersResponse.getValue());
        statistics.setOthers(logService.countByAttribute(logQaRequest));
        return statistics;
    }
}
