package cn.ken.question.answering.system.mapper.knowledge;

import cn.ken.question.answering.system.mapper.BaseMapper;
import cn.ken.question.answering.system.model.knowledge.ExtensionQuestion;

import java.util.List;
import java.util.Map;

public interface ExtensionQuestionMapper extends BaseMapper<ExtensionQuestion,String>{

    List<ExtensionQuestion> getByKnowledgeId(String knowledgeId);

    List<ExtensionQuestion> getAll();

    int countByTitle(List<String> list);

    int countByKnowledgeIdAndTitle(Map map);

    int deleteByKnowledgeId(String knowledgeId);

    int deleteByKnowledgeIdList(List<String> list);

    int batchInsert(List<ExtensionQuestion> list);

    List<String> getByAttribute(String title);
}