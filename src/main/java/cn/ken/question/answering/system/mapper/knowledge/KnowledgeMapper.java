package cn.ken.question.answering.system.mapper.knowledge;

import cn.ken.question.answering.system.mapper.BaseMapper;
import cn.ken.question.answering.system.model.knowledge.Knowledge;
import cn.ken.question.answering.system.model.knowledge.KnowledgeRequest;

import java.util.List;

public interface KnowledgeMapper extends BaseMapper<Knowledge,String> {

    List<Knowledge> getByAttribute(KnowledgeRequest request);

    int countByAttribute(KnowledgeRequest request);

    int countByTitle(String title);

    int countByIdAndTitle(String id,String title);

    int deleteByIdList(List<String> list);
}