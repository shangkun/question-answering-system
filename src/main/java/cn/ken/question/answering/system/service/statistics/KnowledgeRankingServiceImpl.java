package cn.ken.question.answering.system.service.statistics;

import cn.ken.question.answering.system.common.*;
import cn.ken.question.answering.system.common.Enum;
import cn.ken.question.answering.system.mapper.statistics.KnowledegRankingMapper;
import cn.ken.question.answering.system.memorydb.ConfigurationDB;
import cn.ken.question.answering.system.model.knowledge.Knowledge;
import cn.ken.question.answering.system.model.log.LogQa;
import cn.ken.question.answering.system.model.log.LogQaRequest;
import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.model.statistics.KnowledgeRanking;
import cn.ken.question.answering.system.model.statistics.KnowledgeRankingRequest;
import cn.ken.question.answering.system.service.knowledge.KnowledgeService;
import cn.ken.question.answering.system.service.log.LogService;
import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.utils.StringUtils;
import cn.ken.question.answering.system.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * author: shangkun <br/>
 * date: 2018/7/30 <br/>
 * what: KnowledgeRankingServiceImpl <br/>
 */
@Service
public class KnowledgeRankingServiceImpl extends Base implements KnowledgeRankingService{
    @Autowired
    private LogService logService;
    @Autowired
    private KnowledegRankingMapper knowledegRankingMapper;
    @Autowired
    private KnowledgeService knowledgeService;

    private static final List<Integer> HAS_KNOWLEDGE = Arrays.asList(new Integer[]{Enum.hasAnswerAndRecommendResponse.getValue(),Enum.hasAnswerResponse.getValue()});

    @Scheduled(cron="0 0 2 * * ?")
    public void statisticsTask() throws Exception{
        Map<String,String> map = TimeUtils.timeCondition(3,true);
        String startTime = map.get(TimeUtils.START_TIME);
        String endTime = map.get(TimeUtils.END_TIME);
        KnowledgeRankingRequest request = new KnowledgeRankingRequest(startTime,endTime);
        request.setTime(map.get(TimeUtils.TIME));
        List<KnowledgeRanking> knowledgeRankingList = statistics(request);
        if(CollectionUtils.isEmpty(knowledgeRankingList)){
            return;
        }
        knowledegRankingMapper.batchInsert(knowledgeRankingList);
    }

    @Override
    public List<KnowledgeRanking> getHotKnowledge(Integer channelId) throws Exception {
        Map<String,String> map = TimeUtils.timeCondition(2,true);
        String startTime = map.get(TimeUtils.START_TIME);
        String endTime = map.get(TimeUtils.END_TIME);
        KnowledgeRankingRequest request = new KnowledgeRankingRequest(startTime,endTime);
        request.setTime(map.get(TimeUtils.TIME));
        request.setChannelId(channelId);
        List<KnowledgeRanking> knowledgeRankingList = statistics(request);
        if(CollectionUtils.isEmpty(knowledgeRankingList)){
            return Collections.emptyList();
        }
        List<KnowledgeRanking> knowledgeRankingList1 = new ArrayList<>();
        for(KnowledgeRanking knowledgeRanking:knowledgeRankingList){
            Knowledge knowledge = knowledgeService.getKnowledge(knowledgeRanking.getKnowledgeId());
            if(knowledge==null || StringUtils.isEmpty(knowledge.getTitle())){
                continue;
            }
            knowledgeRanking.setKnowledgeTitle(knowledge.getTitle());
            knowledgeRankingList1.add(knowledgeRanking);
        }
        if(knowledgeRankingList1.size()> ConfigurationDB.configuration.getHotQuestionLimit()){
            knowledgeRankingList1 = knowledgeRankingList1.subList(0,ConfigurationDB.configuration.getHotQuestionLimit());
        }
        return knowledgeRankingList1;
    }

    @Override
    public PageData get(KnowledgeRankingRequest knowledgeRankingRequest) throws Exception {
        PageData pageData = new PageData(0);
        int total = knowledegRankingMapper.countByAttribute(knowledgeRankingRequest);
        if(total==0){
            pageData.setData(Collections.emptyList());
            return pageData;
        }
        List<KnowledgeRanking> knowledgeRankingList = knowledegRankingMapper.selectByAttribute(knowledgeRankingRequest);
        for(KnowledgeRanking knowledgeRanking:knowledgeRankingList){
            Knowledge knowledge = knowledgeService.getKnowledge(knowledgeRanking.getKnowledgeId());
            if(knowledge==null || StringUtils.isEmpty(knowledge.getTitle())){
                continue;
            }
            knowledgeRanking.setKnowledgeTitle(knowledge.getTitle());
        }
        pageData.setTotal(total);
        pageData.setData(knowledgeRankingList);
        return pageData;
    }

    @Override
    public List<KnowledgeRanking> statistics(KnowledgeRankingRequest knowledgeRankingRequest) throws Exception {
        LogQaRequest request = new LogQaRequest(knowledgeRankingRequest.getStartTime(),knowledgeRankingRequest.getEndTime());
        request.setResponseTypeList(HAS_KNOWLEDGE);
        if(knowledgeRankingRequest.getChannelId()!=null){
            request.setChannelId(knowledgeRankingRequest.getChannelId());
        }
        List<LogQa> logQaList = logService.getKnowledegRanking(request);
        if(CollectionUtils.isEmpty(logQaList)){
            return Collections.emptyList();
        }
        List<KnowledgeRanking> knowledgeRankingList = new ArrayList<>();
        for(LogQa logQa:logQaList){
            KnowledgeRanking knowledgeRanking = new KnowledgeRanking(logQa.getKnowledgeId(),knowledgeRankingRequest.getTime(),logQa.getAccessNumber());
            knowledgeRankingList.add(knowledgeRanking);
        }
        return knowledgeRankingList;
    }
}
