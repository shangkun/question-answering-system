package cn.ken.questionansweringsystem.mapper.knowledge;

import cn.ken.questionansweringsystem.mapper.BaseMapper;
import cn.ken.questionansweringsystem.model.knowledge.GreetingAnswer;

import java.util.List;

public interface GreetingAnswerMapper extends BaseMapper<GreetingAnswer,String>{

    List<GreetingAnswer> getByGreetingId(String greetingId);

    int deleteByGreetingId(String greetingId);

    int deleteByGreetingIdList(List<String> list);

    int batchInsert(List<GreetingAnswer> list);

    List<String> getByAttribute(String title);

    List<GreetingAnswer> getAll();
}