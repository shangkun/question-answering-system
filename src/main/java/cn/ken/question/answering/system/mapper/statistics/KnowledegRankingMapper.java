package cn.ken.question.answering.system.mapper.statistics;

import cn.ken.question.answering.system.model.statistics.KnowledgeRanking;
import cn.ken.question.answering.system.model.statistics.KnowledgeRankingRequest;

import java.util.List;

public interface KnowledegRankingMapper {
    /**
     * 添加一条数据
     * @param model
     * @return
     */
    int insert(KnowledgeRanking model);

    /**
     * 批量插入数据
     * @param list
     * @return
     */
    int batchInsert(List<KnowledgeRanking> list);

    /**
     * 更新一条数据
     * @param model
     * @return
     */
    int update(KnowledgeRanking model);

    /**
     * 根据属性查询
     * @param request
     * @return
     */
    List<KnowledgeRanking> selectByAttribute(KnowledgeRankingRequest request);

    /**
     * 根据属性计数
     * @param request
     * @return
     */
    int countByAttribute(KnowledgeRankingRequest request);
}