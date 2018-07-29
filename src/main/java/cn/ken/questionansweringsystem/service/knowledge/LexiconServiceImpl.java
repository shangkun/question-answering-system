package cn.ken.questionansweringsystem.service.knowledge;

import cn.ken.questionansweringsystem.common.*;
import cn.ken.questionansweringsystem.common.Enum;
import cn.ken.questionansweringsystem.mapper.knowledge.LexiconMapper;
import cn.ken.questionansweringsystem.model.knowledge.Lexicon;
import cn.ken.questionansweringsystem.model.knowledge.LexiconRequest;
import cn.ken.questionansweringsystem.model.response.PageData;
import cn.ken.questionansweringsystem.utils.Base;
import cn.ken.questionansweringsystem.utils.IdWorker;
import cn.ken.questionansweringsystem.utils.StringUtils;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 词库 <br/>
 */
@Service
public class LexiconServiceImpl extends Base implements LexiconService{
    @Autowired
    private LexiconMapper lexiconMapper;

    @Override
    public void initCustomNature() {
        Nature pcNature = Nature.fromString(Enum.businessWord.getInfo());
        if(pcNature==null){
            Nature.create(Enum.businessWord.getInfo());
        }
    }

    @Override
    public String add(Lexicon request) {
        String result = assemble(request);
        if(result!=null){
            return result;
        }
        lexiconMapper.insert(request);
        String[] topicSet = request.getTopicSet().split(Constant.TOPIC_SPLITER);
        for(String topic:topicSet){
            CustomDictionary.add(topic, Enum.businessWord.getInfo());
        }
        return null;
    }

    @Override
    public Lexicon get(String id) throws Exception {
        if(StringUtils.isEmpty(id)){
            return null;
        }
        return lexiconMapper.selectById(id);
    }

    @Override
    public boolean isRepeat(Lexicon request) {
        if(StringUtils.isEmpty(request.getId()) && !StringUtils.isEmpty(request.getTopic()) && lexiconMapper.countByTopic(request.getTopic())>0){
            return true;
        }
        if(!StringUtils.isEmpty(request.getId()) && !StringUtils.isEmpty(request.getTopic()) && lexiconMapper.countByIdAndTopic(request.getId(), request.getTopic())>0){
            return true;
        }
        return false;
    }

    public String assemble(Lexicon request){
        if(!StringUtils.lengthCheck(request.getTopic(),0,500)){
            return "敏感词主题不能为空或长度超过500";
        }
        if(isRepeat(request)){
            return "敏感词主题重复";
        }
        if(!StringUtils.lengthCheck(request.getTopicSet(),0,4000)){
            return "敏感词集合不能为空或长度超过4000";
        }
        if(StringUtils.isEmpty(request.getModifierId())){
            return "修改人字段不能为空";
        }
        if(request.getStatus()==null){
            return "状态不能为空";
        }
        if(request.getType()==null){
            return "词类型不能为空";
        }
        if(StringUtils.isEmpty(request.getId())){
            request.setId(IdWorker.getInstance().nextId());
            request.setModifyTime(new Date());
        }
        return null;
    }

    @Override
    public String deleteById(String id) throws Exception{
        if(StringUtils.isEmpty(id)){
            return "id不能为空";
        }
        Lexicon lexicon = get(id);
        String[] topicSet = lexicon.getTopicSet().split(Constant.TOPIC_SPLITER);
        for(String topic:topicSet){
            CustomDictionary.remove(topic);
        }
        lexiconMapper.deleteById(id);
        return null;
    }

    @Override
    public String update(Lexicon request) {
        String result = assemble(request);
        if(result!=null){
            return result;
        }
        lexiconMapper.update(request);
        lexiconMapper.insert(request);
        String[] topicSet = request.getTopicSet().split(Constant.TOPIC_SPLITER);
        for(String topic:topicSet){
            CustomDictionary.add(topic, Enum.businessWord.getInfo());
        }
        return null;
    }

    @Override
    public PageData getByAttribute(LexiconRequest request) {
        PageData pageData = new PageData(0);
        int total = lexiconMapper.countByAttribute(request);
        if(total==0){
            pageData.setData(Collections.emptyList());
            return pageData;
        }
        List<Lexicon> lexiconList = lexiconMapper.getByAttribute(request);
        pageData.setTotal(total);
        pageData.setData(lexiconList);
        return pageData;
    }

    @Override
    public String batchInsert(MultipartFile file, LexiconRequest request) {
        return null;
    }

    @Override
    public String batchDelete(List<String> list) throws Exception{
        if(CollectionUtils.isEmpty(list)){
            return "列表不能为空";
        }
        for(String id:list){
            Lexicon lexicon = get(id);
            String[] topicSet = lexicon.getTopicSet().split(Constant.TOPIC_SPLITER);
            for(String topic:topicSet){
                CustomDictionary.remove(topic);
            }
        }
        lexiconMapper.batchDelete(list);
        return null;
    }
}
