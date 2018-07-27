package cn.ken.questionansweringsystem.mapper.knowledge;

import cn.ken.questionansweringsystem.mapper.BaseMapper;
import cn.ken.questionansweringsystem.model.knowledge.Answer;

import java.util.List;

public interface AnswerMapper extends BaseMapper<Answer,String>{

    List<Answer> getByKnowledgeId(String knowledgeId);

    int deleteByKnowledgeId(String knowledgeId);

    int deleteByKnowledgeIdList(List<String> list);

    int batchInsert(List<Answer> list);

    List<String> getByAttribute(String title);
}