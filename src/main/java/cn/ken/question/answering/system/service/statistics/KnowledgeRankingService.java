package cn.ken.question.answering.system.service.statistics;

import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.model.statistics.KnowledgeRanking;
import cn.ken.question.answering.system.model.statistics.KnowledgeRankingRequest;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/30 <br/>
 * what: KnowledgeRankingService <br/>
 */
public interface KnowledgeRankingService {
    /**
     * 获取当天热点知识
     * @param channelId
     * @return
     * @throws Exception
     */
    List<KnowledgeRanking> getHotKnowledge(Integer channelId) throws Exception;

    /**
     * 查询历史统计数据
     * @param knowledgeRankingRequest
     * @return
     * @throws Exception
     */
    PageData get(KnowledgeRankingRequest knowledgeRankingRequest) throws Exception;
    /**
     * 统计
     * @param knowledgeRankingRequest
     * @return
     * @throws Exception
     */
    List<KnowledgeRanking> statistics(KnowledgeRankingRequest knowledgeRankingRequest) throws Exception;
}
