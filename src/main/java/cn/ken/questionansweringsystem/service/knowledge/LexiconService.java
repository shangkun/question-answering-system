package cn.ken.questionansweringsystem.service.knowledge;

import cn.ken.questionansweringsystem.model.knowledge.Lexicon;
import cn.ken.questionansweringsystem.model.knowledge.LexiconRequest;
import cn.ken.questionansweringsystem.model.response.PageData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 词库 <br/>
 */
public interface LexiconService {

    public String add(Lexicon request) throws Exception;

    public Lexicon get(String id) throws Exception;

    public boolean isRepeat(Lexicon request) throws Exception;

    public String deleteById(String id) throws Exception;

    public String update(Lexicon request) throws Exception;

    public PageData getByAttribute(LexiconRequest request) throws Exception;

    public String batchInsert(MultipartFile file,LexiconRequest request) throws Exception;

    public String batchDelete(List<String> list) throws Exception;
}
