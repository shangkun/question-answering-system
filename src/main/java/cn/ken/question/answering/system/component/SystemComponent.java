package cn.ken.question.answering.system.component;

import cn.ken.question.answering.system.common.Constant;
import cn.ken.question.answering.system.memorydb.Admin;
import cn.ken.question.answering.system.model.admin.*;
import cn.ken.question.answering.system.model.configuration.Configuration;
import cn.ken.question.answering.system.model.configuration.SensitiveWordRequest;
import cn.ken.question.answering.system.model.knowledge.*;
import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.utils.IdWorker;
import cn.ken.question.answering.system.common.Enum;
import cn.ken.question.answering.system.memorydb.ConfigurationDB;
import cn.ken.question.answering.system.memorydb.KnowledgeDB;
import cn.ken.question.answering.system.model.configuration.SensitiveWord;
import cn.ken.question.answering.system.service.admin.RoleService;
import cn.ken.question.answering.system.service.admin.UserService;
import cn.ken.question.answering.system.service.configuration.ConfigurationService;
import cn.ken.question.answering.system.service.configuration.SensitiveWordService;
import cn.ken.question.answering.system.service.knowledge.GreetingService;
import cn.ken.question.answering.system.service.knowledge.KnowledgeService;
import cn.ken.question.answering.system.service.knowledge.LexiconService;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/18 <br/>
 * what: SystemComponent <br/>
 */
@Component
public class SystemComponent extends Base {
    @Value("${config.idWorkerNumber}")
    private int idWorkerNumber;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private SensitiveWordService sensitiveWordService;

    @Autowired
    private LexiconService lexiconService;

    @Autowired
    private KnowledgeService knowledgeService;

    @Autowired
    private GreetingService greetingService;

    @PostConstruct
    public void init(){
        logger.info(Constant.printPattern+"system initialization"+Constant.printPattern);
        new IdWorker(idWorkerNumber);
        logger.info(Constant.printPattern+"init admin"+Constant.printPattern);
        initAdmin();
        logger.info(Constant.printPattern+"init config"+Constant.printPattern);
        initConfig();
        logger.info(Constant.printPattern+"init knowledge"+Constant.printPattern);
        initKnowledge();
    }

    /**
     * 管理数据初始化
     * @return
     */
    public boolean initAdmin(){
        try {
            UserRequest userRequest = new UserRequest();
            userRequest.setIndex(0);
            userRequest.setIndex(Constant.BATCH_NUMBER);
            PageData<List<User>> userData = userService.getByAttribute(userRequest);
            Admin.putAllUserMap(userData.getData());

            RoleRequest roleRequest = new RoleRequest();
            roleRequest.setIndex(0);
            roleRequest.setIndex(Constant.BATCH_NUMBER);
            PageData<List<Role>> roleData = roleService.get(roleRequest);
            Admin.putAllRoleMap(roleData.getData());

            List<RoleMenu> roleMenuList = roleService.getRoleMenu();
            Admin.putAllRoleMenuMap(roleMenuList);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
        return true;
    }

    public boolean initConfig(){
        try {
            SensitiveWordRequest sensitiveWordRequest = new SensitiveWordRequest();
            sensitiveWordRequest.setIndex(0);
            sensitiveWordRequest.setPageSize(Integer.MAX_VALUE);
            PageData<List<SensitiveWord>> sensitiveWordData = sensitiveWordService.getByAttribute(sensitiveWordRequest);
            ConfigurationDB.putAllSensitiveWord(sensitiveWordData.getData());

            Configuration configuration = configurationService.get();
            ConfigurationDB.configuration=configuration;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
        return true;
    }

    public boolean initKnowledge(){
        try {
            lexiconService.initCustomNature();

            LexiconRequest lexiconRequest = new LexiconRequest();
            lexiconRequest.setIndex(0);
            lexiconRequest.setPageSize(Integer.MAX_VALUE);
            lexiconRequest.setType(Enum.businessWord.getValue());
            PageData<List<Lexicon>> listPageData = lexiconService.getByAttribute(lexiconRequest);
            for(Lexicon lexicon:listPageData.getData()){
                String[] topicSet = lexicon.getTopicSet().split(Constant.TOPIC_SPLITER);
                for(String topic:topicSet){
                    CustomDictionary.add(topic,Enum.businessWord.getInfo());
                }
            }

            KnowledgeRequest request = new KnowledgeRequest();
            request.setIndex(0);
            request.setPageSize(Integer.MAX_VALUE);
            List<Knowledge> knowledgeList = knowledgeService.getKnowledge(request);
            List<ExtensionQuestion> extensionQuestionList = knowledgeService.getExtensionQuestion();
            List<Answer> answerList = knowledgeService.getAnswer();
            KnowledgeDB.initKnowledge(knowledgeList,extensionQuestionList,answerList);

            GreetingRequest greetingRequest = new GreetingRequest();
            greetingRequest.setIndex(0);
            greetingRequest.setPageSize(Integer.MAX_VALUE);
            List<Greeting> greetingList = greetingService.getGreeting(greetingRequest);
            List<GreetingExtensionQuestion> greetingExtensionQuestionList = greetingService.getGreetingExtensionQuestion();
            List<GreetingAnswer> greetingAnswerList = greetingService.getGreetingAnswer();
            KnowledgeDB.initGreeting(greetingList,greetingExtensionQuestionList,greetingAnswerList);

            KnowledgeDB.initPinYinMap();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }

        return true;
    }
}
