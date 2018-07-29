package cn.ken.questionansweringsystem.component;

import cn.ken.questionansweringsystem.common.*;
import cn.ken.questionansweringsystem.common.Enum;
import cn.ken.questionansweringsystem.memorydb.Admin;
import cn.ken.questionansweringsystem.memorydb.ConfigurationDB;
import cn.ken.questionansweringsystem.memorydb.KnowledgeDB;
import cn.ken.questionansweringsystem.model.admin.*;
import cn.ken.questionansweringsystem.model.configuration.Configuration;
import cn.ken.questionansweringsystem.model.configuration.SensitiveWord;
import cn.ken.questionansweringsystem.model.configuration.SensitiveWordRequest;
import cn.ken.questionansweringsystem.model.knowledge.*;
import cn.ken.questionansweringsystem.model.response.PageData;
import cn.ken.questionansweringsystem.service.admin.RoleService;
import cn.ken.questionansweringsystem.service.admin.UserService;
import cn.ken.questionansweringsystem.service.configuration.ConfigurationService;
import cn.ken.questionansweringsystem.service.configuration.SensitiveWordService;
import cn.ken.questionansweringsystem.service.knowledge.GreetingService;
import cn.ken.questionansweringsystem.service.knowledge.KnowledgeService;
import cn.ken.questionansweringsystem.service.knowledge.LexiconService;
import cn.ken.questionansweringsystem.utils.Base;
import cn.ken.questionansweringsystem.utils.IdWorker;
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
public class SystemComponent extends Base{
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
            PageData<List<Knowledge>> pageData = knowledgeService.get(request);
            KnowledgeDB.initKnowledge(pageData.getData());

            GreetingRequest greetingRequest = new GreetingRequest();
            greetingRequest.setIndex(0);
            greetingRequest.setPageSize(Integer.MAX_VALUE);
            PageData<List<Greeting>> pageData1 = greetingService.get(greetingRequest);
            KnowledgeDB.initGreeting(pageData1.getData());

            KnowledgeDB.initPinYinMap();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }

        return true;
    }
}
