package cn.ken.questionansweringsystem.service.knowledge;

import cn.ken.questionansweringsystem.model.knowledge.ClassificationRequest;
import cn.ken.questionansweringsystem.model.response.PageData;

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

    public boolean isClassificationExists(String id);
}
