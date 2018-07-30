package cn.ken.question.answering.system.service.knowledge;

import cn.ken.question.answering.system.mapper.knowledge.ClassificationMapper;
import cn.ken.question.answering.system.model.knowledge.ClassificationRequest;
import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.utils.IdWorker;
import cn.ken.question.answering.system.utils.StringUtils;
import cn.ken.question.answering.system.model.knowledge.Classification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * author: shangkun <br/>
 * date: 2018/7/26 <br/>
 * what: 分类 <br/>
 */
@Service
public class ClassificationServiceImpl extends Base implements ClassificationService{
    @Autowired
    private ClassificationMapper classificationMapper;

    @Override
    public String add(ClassificationRequest request) throws Exception {
        Classification classification = new Classification();
        String result = assemble(request,classification);
        if(result!=null){
            return result;
        }
        classificationMapper.insert(classification);
        return null;
    }

    public String assemble(ClassificationRequest request,Classification classification) throws Exception{
        if(!StringUtils.lengthCheck(request.getName(), 2, 50)){
            return "分类名称不能小于2或大于50";
        }
        classification.setName(request.getName());
        if(!StringUtils.lengthCheck(request.getDescription(),1,500)){
            return "分类描述不能小于1或大于500";
        }
        classification.setDescription(request.getDescription());
        if(!StringUtils.lengthCheck(request.getModifierId(), 0, 50)){
            return "修改人id不能为空";
        }
        classification.setModifierId(request.getModifierId());
        if (!StringUtils.lengthCheck(request.getpId(),0,20)){
            return "pId不能为空";
        }
        classification.setpId(request.getpId());
        if(isRepeat(request)){
            return "同级分类名称重复";
        }
        if(StringUtils.isEmpty(request.getId())){
            classification.setId(IdWorker.getInstance().nextId());
            classification.setStatus(1);
            classification.setModifyTime(new Date());
        }else{
            if(!isClassificationExists(request.getId())){
                return "当前分类不存在";
            }
            classification.setId(request.getId());
            classification.setpId(null);
        }
        return null;
    }

    @Override
    public boolean isRepeat(ClassificationRequest request) throws Exception {
        if(StringUtils.isEmpty(request.getId()) &&
                !StringUtils.isEmpty(request.getName()) &&
                !StringUtils.isEmpty(request.getpId()) &&
                classificationMapper.countByName(request.getName(),request.getpId())>0){
            return true;
        }
        if(!StringUtils.isEmpty(request.getId()) &&
                !StringUtils.isEmpty(request.getName()) &&
                !StringUtils.isEmpty(request.getpId()) &&
                classificationMapper.countByIdAndName(request.getId(), request.getName(),
                        request.getpId())>0){
            return true;
        }
        return false;
    }

    @Override
    public String delete(String id) throws Exception {
        List<String> idList = new ArrayList<>();
        idList.add(id);
        cascadeGetChildId(idList,id);
        classificationMapper.deleteByIdList(idList);
        return null;
    }

    @Override
    public List<String> getAllChild(String id) throws Exception {
        List<String> idList = new ArrayList<>();
        if(!isClassificationExists(id)){
            return idList;
        }
        idList.add(id);
        cascadeGetChildId(idList,id);
        return idList;
    }

    /**
     * 递归获取孩子节点的id
     * @param idList
     * @param pId
     */
    public void cascadeGetChildId(List<String> idList,String pId){
        List<String> childIdList = classificationMapper.getByPId(pId);
        if(CollectionUtils.isEmpty(childIdList)){
            return;
        }
        idList.addAll(childIdList);
        for(String id:childIdList){
            cascadeGetChildId(idList,id);
        }
    }

    @Override
    public String update(ClassificationRequest request) throws Exception {
        Classification classification = new Classification();
        String result = assemble(request,classification);
        if(result!=null){
            return result;
        }
        classificationMapper.update(classification);
        return null;
    }

    @Override
    public PageData get(ClassificationRequest request) throws Exception {
        PageData pageData = new PageData(0);
        int total = classificationMapper.countByAttribute(request);
        if(total==0){
            pageData.setData(Collections.emptyList());
            return pageData;
        }
        List<Classification> classificationList = classificationMapper.getByAttribute(request);
        pageData.setTotal(total);
        pageData.setData(classificationList);
        return pageData;
    }

    @Override
    public int count(ClassificationRequest request) throws Exception {
        return classificationMapper.countByAttribute(request);
    }

    @Override
    public boolean isClassificationExists(String id) {
        Classification classification = classificationMapper.selectById(id);
        if(classification!=null){
            return true;
        }
        return false;
    }

    @Override
    public Classification getById(String id) {
        return classificationMapper.selectById(id);
    }
}
