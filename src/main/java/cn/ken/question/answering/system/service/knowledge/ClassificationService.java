package cn.ken.question.answering.system.service.knowledge;

import cn.ken.question.answering.system.model.knowledge.ClassificationRequest;
import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.model.knowledge.Classification;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/26 <br/>
 * what: 分类 <br/>
 */
public interface ClassificationService {

    public String add(ClassificationRequest request) throws Exception;

    public boolean isRepeat(ClassificationRequest request) throws Exception;

    public String delete(String id) throws Exception;

    public List<String> getAllChild(String id) throws Exception;

    public String update(ClassificationRequest request) throws Exception;

    public PageData get(ClassificationRequest request) throws Exception;

    public int count(ClassificationRequest request) throws Exception;

    public boolean isClassificationExists(String id);

    public Classification getById(String id);
}
