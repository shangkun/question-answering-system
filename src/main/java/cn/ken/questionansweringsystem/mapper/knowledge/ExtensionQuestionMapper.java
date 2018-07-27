package cn.ken.questionansweringsystem.mapper.knowledge;

import cn.ken.questionansweringsystem.mapper.BaseMapper;
import cn.ken.questionansweringsystem.model.knowledge.ExtensionQuestion;

import java.util.List;
import java.util.Map;

public interface ExtensionQuestionMapper extends BaseMapper<ExtensionQuestion,String>{

    List<ExtensionQuestion> getByKnowledgeId(String knowledgeId);

    int countByTitle(List<String> list);

    int countByKnowledgeIdAndTitle(Map map);

    int deleteByKnowledgeId(String knowledgeId);

    int deleteByKnowledgeIdList(List<String> list);

    int batchInsert(List<ExtensionQuestion> list);

    List<String> getByAttribute(String title);
}