package cn.ken.questionansweringsystem.mapper.knowledge;

import cn.ken.questionansweringsystem.mapper.BaseMapper;
import cn.ken.questionansweringsystem.model.knowledge.Knowledge;
import cn.ken.questionansweringsystem.model.knowledge.KnowledgeRequest;

import java.util.List;

public interface KnowledgeMapper extends BaseMapper<Knowledge,String> {

    List<Knowledge> getByAttribute(KnowledgeRequest request);

    int countByAttribute(KnowledgeRequest request);

    int countByTitle(String title);

    int countByIdAndTitle(String id,String title);

    int deleteByIdList(List<String> list);
}