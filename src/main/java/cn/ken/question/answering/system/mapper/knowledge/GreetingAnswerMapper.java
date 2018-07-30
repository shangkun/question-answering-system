package cn.ken.question.answering.system.mapper.knowledge;

import cn.ken.question.answering.system.model.knowledge.GreetingAnswer;
import cn.ken.question.answering.system.mapper.BaseMapper;

import java.util.List;

public interface GreetingAnswerMapper extends BaseMapper<GreetingAnswer,String>{

    List<GreetingAnswer> getByGreetingId(String greetingId);

    int deleteByGreetingId(String greetingId);

    int deleteByGreetingIdList(List<String> list);

    int batchInsert(List<GreetingAnswer> list);

    List<String> getByAttribute(String title);

    List<GreetingAnswer> getAll();
}