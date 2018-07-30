package cn.ken.question.answering.system.mapper.knowledge;

import cn.ken.question.answering.system.mapper.BaseMapper;
import cn.ken.question.answering.system.model.knowledge.Lexicon;
import cn.ken.question.answering.system.model.knowledge.LexiconRequest;

import java.util.List;

public interface LexiconMapper extends BaseMapper<Lexicon,String>{

    int batchInert(List<Lexicon> list);

    int batchDelete(List<String> list);

    List<Lexicon> getByAttribute(LexiconRequest request);

    int countByAttribute(LexiconRequest request);

    int countByTopic(String topic);

    int countByIdAndTopic(String id,String topic);

}