package cn.ken.questionansweringsystem.service.knowledge;

import cn.ken.questionansweringsystem.mapper.knowledge.GreetingAnswerMapper;
import cn.ken.questionansweringsystem.mapper.knowledge.GreetingExtensionQuestionMapper;
import cn.ken.questionansweringsystem.mapper.knowledge.GreetingMapper;
import cn.ken.questionansweringsystem.memorydb.KnowledgeDB;
import cn.ken.questionansweringsystem.model.admin.User;
import cn.ken.questionansweringsystem.model.knowledge.Greeting;
import cn.ken.questionansweringsystem.model.knowledge.GreetingAnswer;
import cn.ken.questionansweringsystem.model.knowledge.GreetingExtensionQuestion;
import cn.ken.questionansweringsystem.model.knowledge.GreetingRequest;
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
 * what: 寒暄知识 <br/>
 */
@Service
public class GreetingServiceImpl extends Base implements GreetingService {
    @Autowired
    private GreetingMapper greetingMapper;
    @Autowired
    private GreetingExtensionQuestionMapper extensionQuestionMapper;
    @Autowired
    private GreetingAnswerMapper answerMapper;
    @Autowired
    private UserService userService;

    @Override
    public Greeting getGreeting(String greetingId) {
        Greeting greeting = greetingMapper.selectById(greetingId);
        if(greeting==null){
            return null;
        }
        List<GreetingExtensionQuestion> extensionQuestionList = extensionQuestionMapper.getByGreetingId(greetingId);
        List<GreetingAnswer> answerList = answerMapper.getByGreetingId(greetingId);
        if(CollectionUtils.isEmpty(answerList)){
            return null;
        }
        greeting.setGreetingExtensionQuestionList(extensionQuestionList);
        greeting.setGreetingAnswerList(answerList);
        return greeting;
    }

    @Override
    public String add(GreetingRequest request) throws Exception {
        Greeting greeting = new Greeting();
        List<GreetingExtensionQuestion> extensionQuestionList = new ArrayList<>();
        String result = assemble(request,greeting,extensionQuestionList);
        if(result!=null){
            return result;
        }
        greetingMapper.insert(greeting);
        if(!CollectionUtils.isEmpty(extensionQuestionList)){
            extensionQuestionMapper.batchInsert(extensionQuestionList);
        }
        answerMapper.batchInsert(request.getGreetingAnswerList());

        greeting.setGreetingExtensionQuestionList(extensionQuestionList);
        greeting.setGreetingAnswerList(request.getGreetingAnswerList());
        KnowledgeDB.initSingleGreeting(greeting);
        return null;
    }

    public String assemble(GreetingRequest request,Greeting greeting,List<GreetingExtensionQuestion> extensionQuestionList) throws Exception{
        String result = assembleGreeting(request, greeting);
        if(result!=null){
            return result;
        }
        String result1 = assembleGreetingExtensionQuestion(request, greeting, extensionQuestionList);
        if(result1!=null){
            return result1;
        }
        String result2 = assembleGreetingAnswer(request, greeting);
        if(result2!=null){
            return result2;
        }
        return null;
    }

    public String assembleGreeting(GreetingRequest request,Greeting greeting) throws Exception{
        if(!StringUtils.lengthCheck(request.getTitle(),0,300)){
            return "知识标题不能为空或长度超过300";
        }
        greeting.setTitle(request.getTitle());
        if(StringUtils.isEmpty(request.getModifierId())){
            return "修改人id不能为空";
        }
        greeting.setModifierId(request.getModifierId());
        if(isRepeat(request)){
            return "知识标题重复";
        }
        if(StringUtils.isEmpty(request.getId())){
            greeting.setId(IdWorker.getInstance().nextId());
            greeting.setModifyTime(new Date());
        }else{
            Greeting greeting1 = greetingMapper.selectById(request.getId());
            if(greeting1==null){
                return "当前知识不存在";
            }
            greeting.setId(request.getId());
        }
        return null;
    }

    public String assembleGreetingExtensionQuestion(GreetingRequest request,Greeting greeting,List<GreetingExtensionQuestion> extensionQuestionList) throws Exception{
        if(CollectionUtils.isEmpty(request.getGreetingExtensionQuestionTitleList())){
            return null;
        }
        if(isGreetingExtensionQuestionRepeat(request)){
            return "扩展问重复";
        }
        for(String title:request.getGreetingExtensionQuestionTitleList()){
            if(!StringUtils.lengthCheck(title,0,500) || title.equals(greeting.getTitle())){
                continue;
            }
            GreetingExtensionQuestion extensionQuestion = new GreetingExtensionQuestion();
            extensionQuestion.setId(IdWorker.getInstance().nextId());
            extensionQuestion.setTitle(title);
            extensionQuestion.setGreetingId(greeting.getId());
            extensionQuestionList.add(extensionQuestion);
        }
        if(CollectionUtils.isEmpty(extensionQuestionList)){
            return "扩展问不能为空或长度超过500,扩展问不能与知识标题重复";
        }
        return null;
    }

    public String assembleGreetingAnswer(GreetingRequest request,Greeting greeting){
        if(CollectionUtils.isEmpty(request.getGreetingAnswerList())){
            return "答案不能为空";
        }
        for(GreetingAnswer answer:request.getGreetingAnswerList()){
            if(!StringUtils.lengthCheck(answer.getAnswer(),0,2000) || answer.getChannelId()==null){
                return "答案不能为空或长度超过2000,渠道不能为空";
            }
//            String result = ConfigurationDB.isSensitiveWord(answer.getContent());
//            if(result!=null){
//                return "答案中含有敏感词";
//            }
            answer.setId(IdWorker.getInstance().nextId());
            answer.setGreetingId(greeting.getId());
        }
        return null;
    }

    @Override
    public boolean isRepeat(GreetingRequest request) throws Exception {
        if(StringUtils.isEmpty(request.getId()) &&
                !StringUtils.isEmpty(request.getTitle()) &&
                greetingMapper.countByTitle(request.getTitle())>0){
            return true;
        }
        if(!StringUtils.isEmpty(request.getId()) &&
                !StringUtils.isEmpty(request.getTitle()) &&
                greetingMapper.countByIdAndTitle(request.getId(), request.getTitle())>0){
            return true;
        }
        List<String> titleList = new ArrayList<>();
        titleList.add(request.getTitle());
        if(isGreetingExtensionQuestionRepeat(titleList, request.getId())){
            return true;
        }
        return false;
    }

    @Override
    public boolean isGreetingExtensionQuestionRepeat(GreetingRequest request) throws Exception {
        if(CollectionUtils.isEmpty(request.getGreetingExtensionQuestionTitleList())){
            return false;
        }
        return isGreetingExtensionQuestionRepeat(request.getGreetingExtensionQuestionTitleList(), request.getId());
    }

    public boolean isGreetingExtensionQuestionRepeat(List<String> extensionQuestionList,String greetingId) throws Exception {
        if(StringUtils.isEmpty(greetingId) &&
                !CollectionUtils.isEmpty(extensionQuestionList) &&
                extensionQuestionMapper.countByTitle(extensionQuestionList)>0){
            return true;
        }
        if(!StringUtils.isEmpty(greetingId) &&
                !CollectionUtils.isEmpty(extensionQuestionList)){
            Map<String,Object> map = new HashMap<>();
            map.put("greetingId",greetingId);
            map.put("list",extensionQuestionList);
            if(extensionQuestionMapper.countByGreetingIdAndTitle(map)>0){
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
        greetingMapper.deleteById(id);
        extensionQuestionMapper.deleteByGreetingId(id);
        answerMapper.deleteByGreetingId(id);
        KnowledgeDB.greetingMap.remove(id);
        return null;
    }

    @Override
    public String deleteByIdList(List<String> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return "至少选择一个知识";
        }
        greetingMapper.deleteByIdList(list);
        extensionQuestionMapper.deleteByGreetingIdList(list);
        answerMapper.deleteByGreetingIdList(list);
        for(String id:list){
            KnowledgeDB.greetingMap.remove(id);
        }
        return null;
    }

    @Override
    public String update(GreetingRequest request) throws Exception {
        Greeting greeting = new Greeting();
        List<GreetingExtensionQuestion> extensionQuestionList = new ArrayList<>();
        String result = assemble(request,greeting,extensionQuestionList);
        if(result!=null){
            return result;
        }
        greetingMapper.update(greeting);
        extensionQuestionMapper.deleteByGreetingId(greeting.getId());
        extensionQuestionMapper.batchInsert(extensionQuestionList);
        answerMapper.deleteByGreetingId(greeting.getId());
        answerMapper.batchInsert(request.getGreetingAnswerList());

        greeting.setGreetingExtensionQuestionList(extensionQuestionList);
        greeting.setGreetingAnswerList(request.getGreetingAnswerList());
        KnowledgeDB.initSingleGreeting(greeting);
        return null;
    }

    @Override
    public PageData get(GreetingRequest request) throws Exception {
        PageData pageData = new PageData(0);
        if(StringUtils.isEmpty(request.getTitle())){
            List<String> greetingIdList = extensionQuestionMapper.getByAttribute(request.getTitle());
            if(!CollectionUtils.isEmpty(greetingIdList)){
                request.setIdList(greetingIdList);
            }
        }
        if(StringUtils.isEmpty(request.getTitle())){
            List<String> greetingIdList = answerMapper.getByAttribute(request.getTitle());
            if(!CollectionUtils.isEmpty(greetingIdList)){
                request.getIdList().addAll(greetingIdList);
            }
        }
        int total = greetingMapper.countByAttribute(request);
        if(total==0){
            pageData.setData(Collections.emptyList());
            return pageData;
        }
        List<Greeting> greetingList = greetingMapper.getByAttribute(request);
        for(Greeting greeting:greetingList){
            List<GreetingExtensionQuestion> extensionQuestionList = extensionQuestionMapper.getByGreetingId(greeting.getId());
            List<GreetingAnswer> answerList = answerMapper.getByGreetingId(greeting.getId());
            if(CollectionUtils.isEmpty(answerList)){
                continue;
            }
            greeting.setGreetingExtensionQuestionList(extensionQuestionList);
            greeting.setGreetingAnswerList(answerList);
            User user = userService.getById(greeting.getModifierId());
            if(user!=null){
                greeting.setModifierName(user.getAccount());
            }
        }
        pageData.setTotal(total);
        pageData.setData(greetingList);
        return pageData;
    }
}
