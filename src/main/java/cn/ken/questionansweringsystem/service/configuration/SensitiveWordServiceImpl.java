package cn.ken.questionansweringsystem.service.configuration;

import cn.ken.questionansweringsystem.mapper.configuration.SensitiveWordMapper;
import cn.ken.questionansweringsystem.memorydb.ConfigurationDB;
import cn.ken.questionansweringsystem.model.response.PageData;
import cn.ken.questionansweringsystem.model.configuration.SensitiveWord;
import cn.ken.questionansweringsystem.model.configuration.SensitiveWordRequest;
import cn.ken.questionansweringsystem.utils.Base;
import cn.ken.questionansweringsystem.utils.IdWorker;
import cn.ken.questionansweringsystem.utils.StringUtils;
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
 * what: 敏感词 <br/>
 */
@Service
public class SensitiveWordServiceImpl extends Base implements SensitiveWordService{
    @Autowired
    private SensitiveWordMapper sensitiveWordMapper;

    @Override
    public String add(SensitiveWord request) {
        String result = assemble(request);
        if(result!=null){
            return result;
        }
        sensitiveWordMapper.insert(request);
        ConfigurationDB.putOneSensitiveWord(request);
        return null;
    }

    @Override
    public SensitiveWord get(String id) throws Exception {
        if(StringUtils.isEmpty(id)){
            return null;
        }
        return sensitiveWordMapper.selectById(id);
    }

    @Override
    public boolean isRepeat(SensitiveWord request) {
        if(StringUtils.isEmpty(request.getId()) && !StringUtils.isEmpty(request.getTopic()) && sensitiveWordMapper.countByTopic(request.getTopic())>0){
            return true;
        }
        if(!StringUtils.isEmpty(request.getId()) && !StringUtils.isEmpty(request.getTopic()) && sensitiveWordMapper.countByIdAndTopic(request.getId(), request.getTopic())>0){
            return true;
        }
        return false;
    }

    public String assemble(SensitiveWord request){
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
        if(StringUtils.isEmpty(request.getId())){
            request.setId(IdWorker.getInstance().nextId());
            request.setModifyTime(new Date());
        }
        return null;
    }

    @Override
    public String deleteById(String id) {
        if(StringUtils.isEmpty(id)){
            return "id不能为空";
        }
        SensitiveWord sensitiveWord = sensitiveWordMapper.selectById(id);
        if(sensitiveWord==null){
            return "不存在该条记录";
        }
        sensitiveWordMapper.deleteById(id);
        ConfigurationDB.sensitiveWordMap.remove(sensitiveWord.getTopic());
        return null;
    }

    @Override
    public String update(SensitiveWord request) {
        String result = assemble(request);
        if(result!=null){
            return result;
        }
        sensitiveWordMapper.update(request);
        ConfigurationDB.putOneSensitiveWord(request);
        return null;
    }

    @Override
    public PageData getByAttribute(SensitiveWordRequest request) {
        PageData pageData = new PageData(0);
        int total = sensitiveWordMapper.countByAttribute(request);
        if(total==0){
            pageData.setData(Collections.emptyList());
            return pageData;
        }
        List<SensitiveWord> sensitiveWordList = sensitiveWordMapper.getByAttribute(request);
        pageData.setTotal(total);
        pageData.setData(sensitiveWordList);
        return pageData;
    }

    @Override
    public String batchInsert(MultipartFile file, SensitiveWordRequest request) {
        return null;
    }

    @Override
    public String batchDelete(List<String> list) {
        if(CollectionUtils.isEmpty(list)){
            return "列表不能为空";
        }
        sensitiveWordMapper.batchDelete(list);
        for(String id:list){
            return deleteById(id);
        }
        return null;
    }
}
