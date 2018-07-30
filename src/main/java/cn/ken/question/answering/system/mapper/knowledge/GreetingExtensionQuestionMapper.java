package cn.ken.question.answering.system.mapper.knowledge;

import cn.ken.question.answering.system.mapper.BaseMapper;
import cn.ken.question.answering.system.model.knowledge.GreetingExtensionQuestion;

import java.util.List;
import java.util.Map;

public interface GreetingExtensionQuestionMapper extends BaseMapper<GreetingExtensionQuestion,String> {

    List<GreetingExtensionQuestion> getByGreetingId(String knowledgeId);

    int countByTitle(List<String> list);

    int countByGreetingIdAndTitle(Map map);

    int deleteByGreetingId(String knowledgeId);

    int deleteByGreetingIdList(List<String> list);

    int batchInsert(List<GreetingExtensionQuestion> list);

    List<String> getByAttribute(String title);

    List<GreetingExtensionQuestion> getAll();
}