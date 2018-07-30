package cn.ken.questionansweringsystem.service.knowledge;

import cn.ken.questionansweringsystem.mapper.knowledge.AnswerMapper;
import cn.ken.questionansweringsystem.mapper.knowledge.ExtensionQuestionMapper;
import cn.ken.questionansweringsystem.mapper.knowledge.KnowledgeMapper;
import cn.ken.questionansweringsystem.memorydb.KnowledgeDB;
import cn.ken.questionansweringsystem.model.admin.User;
import cn.ken.questionansweringsystem.model.knowledge.*;
import cn.ken.questionansweringsystem.model.response.PageData;
import cn.ken.questionansweringsystem.service.admin.UserService;
import cn.ken.questionansweringsystem.utils.Base;
import cn.ken.questionansweringsystem.utils.IdWorker;
import cn.ken.questionansweringsystem.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * author: shangkun <br/>
 * date: 2018/7/27 <br/>
 * what: 知识 <br/>
 */
@Service
public class KnowledgeServiceImpl extends Base implements KnowledgeService{
    @Autowired
    private KnowledgeMapper knowledgeMapper;
    @Autowired
    private ExtensionQuestionMapper extensionQuestionMapper;
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private ClassificationService classificationService;
    @Autowired
    private UserService userService;

    @Override
    public Knowledge getKnowledge(String knowledgeId) {
        Knowledge knowledge = knowledgeMapper.selectById(knowledgeId);
        if(knowledge==null){
            return null;
        }
        List<ExtensionQuestion> extensionQuestionList = extensionQuestionMapper.getByKnowledgeId(knowledgeId);
        List<Answer> answerList = answerMapper.getByKnowledgeId(knowledgeId);
        if(CollectionUtils.isEmpty(answerList)){
            return null;
        }
        Classification classification = classificationService.getById(knowledge.getClassificationId());
        if(classification!=null){
            knowledge.setClassificationName(classification.getName());
        }
        knowledge.setExtensionQuestionList(extensionQuestionList);
        knowledge.setAnswerList(answerList);
        return knowledge;
    }

    @Override
    public String add(KnowledgeRequest request) throws Exception {
        Knowledge knowledge = new Knowledge();
        List<ExtensionQuestion> extensionQuestionList = new ArrayList<>();
        String result = assemble(request,knowledge,extensionQuestionList);
        if(result!=null){
            return result;
        }
        knowledgeMapper.insert(knowledge);
        if(!CollectionUtils.isEmpty(extensionQuestionList)){
            extensionQuestionMapper.batchInsert(extensionQuestionList);
        }
        answerMapper.batchInsert(request.getAnswerList());

        Classification classification = classificationService.getById(knowledge.getClassificationId());
        if(classification!=null){
            knowledge.setClassificationName(classification.getName());
        }
        knowledge.setExtensionQuestionList(extensionQuestionList);
        knowledge.setAnswerList(request.getAnswerList());
        KnowledgeDB.initSingleKnowledge(knowledge);
        return null;
    }

    public String assemble(KnowledgeRequest request,Knowledge knowledge,List<ExtensionQuestion> extensionQuestionList) throws Exception{
        String result = assembleKnowledge(request,knowledge);
        if(result!=null){
            return result;
        }
        String result1 = assembleExtensionQuestion(request, knowledge, extensionQuestionList);
        if(result1!=null){
            return result1;
        }
        String result2 = assembleAnswer(request, knowledge);
        if(result2!=null){
            return result2;
        }
        return null;
    }

    public String assembleKnowledge(KnowledgeRequest request,Knowledge knowledge) throws Exception{
        if(!StringUtils.lengthCheck(request.getTitle(),0,300)){
            return "知识标题不能为空或长度超过300";
        }
        knowledge.setTitle(request.getTitle());
        if(StringUtils.isEmpty(request.getClassificationId())){
            return "分类不能为空";
        }
        if(!classificationService.isClassificationExists(request.getClassificationId())){
            return "分类不存在";
        }
        knowledge.setClassificationId(request.getClassificationId());
        if(StringUtils.isEmpty(request.getModifierId())){
            return "修改人id不能为空";
        }
        knowledge.setModifierId(request.getModifierId());
        if(request.getStatus()==null){
            return "状态不能为空";
        }
        if(!StringUtils.isEmpty(request.getTeacherId()) && request.getTeacherId().length()>50){
            return "教师id长度不能超过50";
        }
        knowledge.setStatus(request.getStatus());
        if(isRepeat(request)){
            return "知识标题重复";
        }
        if(StringUtils.isEmpty(request.getId())){
            knowledge.setId(IdWorker.getInstance().nextId());
            knowledge.setModifyTime(new Date());
        }else{
            Knowledge knowledge1 = knowledgeMapper.selectById(request.getId());
            if(knowledge1==null){
                return "当前知识不存在";
            }
            knowledge.setId(request.getId());
        }
        return null;
    }

    public String assembleExtensionQuestion(KnowledgeRequest request,Knowledge knowledge,List<ExtensionQuestion> extensionQuestionList) throws Exception{
        if(CollectionUtils.isEmpty(request.getExtensionQuestionTitleList())){
            return null;
        }
        if(isExtensionQuestionRepeat(request)){
            return "扩展问重复";
        }
        for(String title:request.getExtensionQuestionTitleList()){
            if(!StringUtils.lengthCheck(title,0,500) || title.equals(knowledge.getTitle())){
                continue;
            }
            ExtensionQuestion extensionQuestion = new ExtensionQuestion();
            extensionQuestion.setId(IdWorker.getInstance().nextId());
            extensionQuestion.setTitle(title);
            extensionQuestion.setKnowledgeId(knowledge.getId());
            extensionQuestionList.add(extensionQuestion);
        }
        if(CollectionUtils.isEmpty(extensionQuestionList)){
            return "扩展问不能为空或长度超过500,扩展问不能与知识标题重复";
        }
        return null;
    }

    public String assembleAnswer(KnowledgeRequest request,Knowledge knowledge){
        if(CollectionUtils.isEmpty(request.getAnswerList())){
            return "答案不能为空";
        }
        for(Answer answer:request.getAnswerList()){
            if(!StringUtils.lengthCheck(answer.getContent(),0,2000) || answer.getChannelId()==null){
                return "答案不能为空或长度超过2000,渠道不能为空";
            }
//            String result = ConfigurationDB.isSensitiveWord(answer.getContent());
//            if(result!=null){
//                return "答案中含有敏感词";
//            }
            answer.setId(IdWorker.getInstance().nextId());
            answer.setKnowledgeId(knowledge.getId());
        }
        return null;
    }

    @Override
    public boolean isRepeat(KnowledgeRequest request) throws Exception {
        if(StringUtils.isEmpty(request.getId()) &&
                !StringUtils.isEmpty(request.getTitle()) &&
                knowledgeMapper.countByTitle(request.getTitle())>0){
            return true;
        }
        if(!StringUtils.isEmpty(request.getId()) &&
                !StringUtils.isEmpty(request.getTitle()) &&
                knowledgeMapper.countByIdAndTitle(request.getId(), request.getTitle())>0){
            return true;
        }
        List<String> titleList = new ArrayList<>();
        titleList.add(request.getTitle());
        if(isExtensionQuestionRepeat(titleList,request.getId())){
            return true;
        }
        return false;
    }

    @Override
    public boolean isExtensionQuestionRepeat(KnowledgeRequest request) throws Exception {
        if(CollectionUtils.isEmpty(request.getExtensionQuestionTitleList())){
            return false;
        }
        return isExtensionQuestionRepeat(request.getExtensionQuestionTitleList(),request.getId());
    }

    public boolean isExtensionQuestionRepeat(List<String> extensionQuestionList,String knowledgeId) throws Exception {
        if(StringUtils.isEmpty(knowledgeId) &&
                !CollectionUtils.isEmpty(extensionQuestionList) &&
                extensionQuestionMapper.countByTitle(extensionQuestionList)>0){
            return true;
        }
        if(!StringUtils.isEmpty(knowledgeId) &&
                !CollectionUtils.isEmpty(extensionQuestionList)){
            Map<String,Object> map = new HashMap<>();
            map.put("knowledgeId",knowledgeId);
            map.put("list",extensionQuestionList);
            if(extensionQuestionMapper.countByKnowledgeIdAndTitle(map)>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public String delete(String id) throws Exception {
        if(StringUtils.isEmpty(id)){
            return "知识id不能为空";
        }
        knowledgeMapper.deleteById(id);
        extensionQuestionMapper.deleteByKnowledgeId(id);
        answerMapper.deleteByKnowledgeId(id);
        KnowledgeDB.knowledgeMap.remove(id);
        return null;
    }

    @Override
    public String deleteByIdList(List<String> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return "至少选择一个知识";
        }
        knowledgeMapper.deleteByIdList(list);
        extensionQuestionMapper.deleteByKnowledgeIdList(list);
        answerMapper.deleteByKnowledgeIdList(list);
        for(String id:list){
            KnowledgeDB.knowledgeMap.remove(id);
        }
        return null;
    }

    @Override
    public String update(KnowledgeRequest request) throws Exception {
        Knowledge knowledge = new Knowledge();
        List<ExtensionQuestion> extensionQuestionList = new ArrayList<>();
        String result = assemble(request,knowledge,extensionQuestionList);
        if(result!=null){
            return result;
        }
        knowledgeMapper.update(knowledge);
        extensionQuestionMapper.deleteByKnowledgeId(knowledge.getId());
        extensionQuestionMapper.batchInsert(extensionQuestionList);
        answerMapper.deleteByKnowledgeId(knowledge.getId());
        answerMapper.batchInsert(request.getAnswerList());

        Classification classification = classificationService.getById(knowledge.getClassificationId());
        if(classification!=null){
            knowledge.setClassificationName(classification.getName());
        }
        knowledge.setExtensionQuestionList(extensionQuestionList);
        knowledge.setAnswerList(request.getAnswerList());
        KnowledgeDB.initSingleKnowledge(knowledge);
        return null;
    }

    @Override
    public PageData get(KnowledgeRequest request) throws Exception {
        PageData pageData = new PageData(0);
        if(!StringUtils.isEmpty(request.getClassificationId())){
            request.setClassificationIdList(classificationService.getAllChild(request.getClassificationId()));
        }
        if(StringUtils.isEmpty(request.getTitle())){
            List<String> knowledgeIdList = extensionQuestionMapper.getByAttribute(request.getTitle());
            if(!CollectionUtils.isEmpty(knowledgeIdList)){
                request.setIdList(knowledgeIdList);
            }
        }
        if(StringUtils.isEmpty(request.getTitle())){
            List<String> knowledgeIdList = answerMapper.getByAttribute(request.getTitle());
            if(!CollectionUtils.isEmpty(knowledgeIdList)){
                request.getIdList().addAll(knowledgeIdList);
            }
        }
        int total = knowledgeMapper.countByAttribute(request);
        if(total==0){
            pageData.setData(Collections.emptyList());
            return pageData;
        }
        List<Knowledge> knowledgeList = knowledgeMapper.getByAttribute(request);
        for(Knowledge knowledge:knowledgeList){
            List<ExtensionQuestion> extensionQuestionList = extensionQuestionMapper.getByKnowledgeId(knowledge.getId());
            List<Answer> answerList = answerMapper.getByKnowledgeId(knowledge.getId());
            if(CollectionUtils.isEmpty(answerList)){
                continue;
            }
            knowledge.setExtensionQuestionList(extensionQuestionList);
            knowledge.setAnswerList(answerList);
            User user = userService.getById(knowledge.getModifierId());
            if(user!=null){
                knowledge.setModifierName(user.getAccount());
            }
            Classification classification = classificationService.getById(knowledge.getClassificationId());
            if(classification!=null){
                knowledge.setClassificationName(classification.getName());
            }
        }
        pageData.setTotal(total);
        pageData.setData(knowledgeList);
        return pageData;
    }

    @Override
    public List<Knowledge> getKnowledge(KnowledgeRequest request) throws Exception {
        return knowledgeMapper.getByAttribute(request);
    }

    @Override
    public List<ExtensionQuestion> getExtensionQuestion() throws Exception {
        return extensionQuestionMapper.getAll();
    }

    @Override
    public List<Answer> getAnswer() throws Exception {
        return answerMapper.getAll();
    }
}
