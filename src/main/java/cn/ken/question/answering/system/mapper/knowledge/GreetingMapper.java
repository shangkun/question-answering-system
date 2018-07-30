package cn.ken.question.answering.system.mapper.knowledge;

import cn.ken.question.answering.system.model.knowledge.Greeting;
import cn.ken.question.answering.system.model.knowledge.GreetingRequest;
import cn.ken.question.answering.system.mapper.BaseMapper;

import java.util.List;

public interface GreetingMapper extends BaseMapper<Greeting,String>{

    List<Greeting> getByAttribute(GreetingRequest request);

    int countByAttribute(GreetingRequest request);

    int countByTitle(String title);

    int countByIdAndTitle(String id,String title);

    int deleteByIdList(List<String> list);

    int batchInsert(List<Greeting> list);
}