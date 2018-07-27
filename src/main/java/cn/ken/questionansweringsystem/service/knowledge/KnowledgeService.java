package cn.ken.questionansweringsystem.service.knowledge;

import cn.ken.questionansweringsystem.model.knowledge.Knowledge;
import cn.ken.questionansweringsystem.model.knowledge.KnowledgeRequest;
import cn.ken.questionansweringsystem.model.response.PageData;

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
}
