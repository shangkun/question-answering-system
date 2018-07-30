package cn.ken.question.answering.system.mapper.knowledge;

import cn.ken.question.answering.system.model.knowledge.Answer;
import cn.ken.question.answering.system.mapper.BaseMapper;

import java.util.List;

public interface AnswerMapper extends BaseMapper<Answer,String>{

    List<Answer> getByKnowledgeId(String knowledgeId);

    List<Answer> getAll();

    int deleteByKnowledgeId(String knowledgeId);

    int deleteByKnowledgeIdList(List<String> list);

    int batchInsert(List<Answer> list);

    List<String> getByAttribute(String title);
}