package cn.ken.question.answering.system.service.statistics;

import cn.ken.question.answering.system.mapper.statistics.InfoStatisticsMapper;
import cn.ken.question.answering.system.model.knowledge.ClassificationRequest;
import cn.ken.question.answering.system.model.knowledge.GreetingRequest;
import cn.ken.question.answering.system.model.knowledge.KnowledgeRequest;
import cn.ken.question.answering.system.model.knowledge.LexiconRequest;
import cn.ken.question.answering.system.model.statistics.InfoStatistics;
import cn.ken.question.answering.system.model.statistics.InfoStatisticsRequest;
import cn.ken.question.answering.system.service.knowledge.ClassificationService;
import cn.ken.question.answering.system.service.knowledge.GreetingService;
import cn.ken.question.answering.system.service.knowledge.KnowledgeService;
import cn.ken.question.answering.system.service.knowledge.LexiconService;
import cn.ken.question.answering.system.service.log.LogService;
import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author: shangkun <br/>
 * date: 2018/7/30 <br/>
 * what: InfoStatisticsServiceImpl <br/>
 */
@Service
public class InfoStatisticsServiceImpl extends Base implements InfoStatisticsService{
    @Autowired
    private KnowledgeService knowledgeService;
    @Autowired
    private ClassificationService classificationService;
    @Autowired
    private LexiconService lexiconService;
    @Autowired
    private GreetingService greetingService;
    @Autowired
    private LogService logService;
    @Autowired
    private InfoStatisticsMapper infoStatisticsMapper;
    @Override
    public List<InfoStatistics> get() throws Exception {
        InfoStatistics today = null;
        List<InfoStatistics> infoStatisticsList = new ArrayList<>();
        for(int i=1;i<=5;i++){
            InfoStatistics infoStatistics = null;
            String startTime = null;
            String endTime = null;
            if(i==2){
                Map<String,String> map = TimeUtils.timeCondition(i,true);
                startTime = map.get(TimeUtils.START_TIME);
                endTime = map.get(TimeUtils.END_TIME);
                InfoStatisticsRequest request = new InfoStatisticsRequest(startTime,endTime);
                request.setTime(map.get(TimeUtils.TIME));
                infoStatistics = statistics(request);
                today = infoStatistics;
            }else{
                Map<String,String> map = TimeUtils.timeCondition(i,false);
                startTime = map.get(TimeUtils.START_TIME);
                endTime = map.get(TimeUtils.END_TIME);
                infoStatistics = infoStatisticsMapper.selectByTime(startTime,endTime);
                if(infoStatistics==null){
                    infoStatistics = new InfoStatistics(0,0,0,0,0);
                }
            }
            infoStatistics.setType(i);
            infoStatisticsList.add(infoStatistics);
        }
        //累加今天的数据到全部、本周和本月
        for(InfoStatistics infoStatistics:infoStatisticsList){
            if(infoStatistics.getType()==2 || infoStatistics.getType()==3){
                continue;
            }
            infoStatistics.setLexiconAccumulation(today.getLexicon());
            infoStatistics.setKnowledgeAccumulation(today.getKnowledge());
            infoStatistics.setGreetingAccumulation(today.getGreeting());
            infoStatistics.setQaAccumulation(today.getQa());
            infoStatistics.setClassificationAccumulation(today.getClassification());
        }
        return infoStatisticsList;
    }

    @Scheduled(cron="0 0 1 * * ?")
    public void statisticTask() throws Exception{
        Map<String,String> map = TimeUtils.timeCondition(3,true);
        String startTime = map.get(TimeUtils.START_TIME);
        String endTime = map.get(TimeUtils.END_TIME);
        InfoStatisticsRequest request = new InfoStatisticsRequest(startTime,endTime);
        request.setTime(map.get(TimeUtils.TIME));
        InfoStatistics infoStatistics = statistics(request);
        infoStatisticsMapper.insert(infoStatistics);
    }

    @Override
    public InfoStatistics statistics(InfoStatisticsRequest request) throws Exception {
        InfoStatistics infoStatistics = new InfoStatistics();
        String startTime = request.getStartTime();
        String endTime = request.getEndTime();
        infoStatistics.setTime(request.getTime());
        LexiconRequest lexiconRequest = new LexiconRequest(startTime,endTime);
        infoStatistics.setLexicon(lexiconService.countByAttribute(lexiconRequest));
        KnowledgeRequest knowledgeRequest = new KnowledgeRequest(startTime,endTime);
        infoStatistics.setKnowledge(knowledgeService.count(knowledgeRequest));
        GreetingRequest greetingRequest = new GreetingRequest(startTime,endTime);
        infoStatistics.setGreeting(greetingService.count(greetingRequest));
        ClassificationRequest classificationRequest = new ClassificationRequest(startTime,endTime);
        infoStatistics.setClassification(classificationService.count(classificationRequest));
        infoStatistics.setQa(logService.count(startTime,endTime));
        return infoStatistics;
    }
}
