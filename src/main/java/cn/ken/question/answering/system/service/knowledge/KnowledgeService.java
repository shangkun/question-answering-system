package cn.ken.question.answering.system.service.knowledge;

import cn.ken.question.answering.system.model.knowledge.Answer;
import cn.ken.question.answering.system.model.knowledge.ExtensionQuestion;
import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.model.knowledge.Knowledge;
import cn.ken.question.answering.system.model.knowledge.KnowledgeRequest;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/27 <br/>
 * what: 知识 <br/>
 */
public interface KnowledgeService {

    public Knowledge getKnowledge(String knowledgeId);

    public String add(KnowledgeRequest request) throws Exception;

    public boolean isRepeat(KnowledgeRequest request) throws Exception;

    public boolean isExtensionQuestionRepeat(KnowledgeRequest request) throws Exception;

    public String delete(String id) throws Exception;

    public String deleteByIdList(List<String> list) throws Exception;

    public String update(KnowledgeRequest request) throws Exception;

    public PageData get(KnowledgeRequest request) throws Exception;

    public int count(KnowledgeRequest request) throws Exception;

    public List<Knowledge> getKnowledge(KnowledgeRequest request) throws Exception;

    public List<ExtensionQuestion> getExtensionQuestion() throws Exception;

    public List<Answer> getAnswer() throws Exception;
}
