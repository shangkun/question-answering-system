package cn.ken.questionansweringsystem.mapper.knowledge;

import cn.ken.questionansweringsystem.mapper.BaseMapper;
import cn.ken.questionansweringsystem.model.knowledge.GreetingExtensionQuestion;

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
}