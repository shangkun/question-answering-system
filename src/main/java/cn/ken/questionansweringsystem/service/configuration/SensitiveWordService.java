package cn.ken.questionansweringsystem.service.configuration;

import cn.ken.questionansweringsystem.model.response.PageData;
import cn.ken.questionansweringsystem.model.configuration.SensitiveWord;
import cn.ken.questionansweringsystem.model.configuration.SensitiveWordRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 敏感词 <br/>
 */
public interface SensitiveWordService {

    public String add(SensitiveWord request) throws Exception;

    public SensitiveWord get(String id) throws Exception;

    public boolean isRepeat(SensitiveWord request) throws Exception;

    public String deleteById(String id) throws Exception;

    public String update(SensitiveWord request) throws Exception;

    public PageData getByAttribute(SensitiveWordRequest request) throws Exception;

    public String batchInsert(MultipartFile file,SensitiveWordRequest request) throws Exception;

    public String batchDelete(List<String> list) throws Exception;
}
