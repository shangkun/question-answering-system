package cn.ken.question.answering.system.service.knowledge;

import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.model.knowledge.Lexicon;
import cn.ken.question.answering.system.model.knowledge.LexiconRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 词库 <br/>
 */
public interface LexiconService {

    public void initCustomNature();

    public String add(Lexicon request) throws Exception;

    public Lexicon get(String id) throws Exception;

    public boolean isRepeat(Lexicon request) throws Exception;

    public String deleteById(String id) throws Exception;

    public String update(Lexicon request) throws Exception;

    public PageData getByAttribute(LexiconRequest request) throws Exception;

    public int countByAttribute(LexiconRequest request) throws Exception;

    public String batchInsert(MultipartFile file,LexiconRequest request) throws Exception;

    public String batchDelete(List<String> list) throws Exception;
}
