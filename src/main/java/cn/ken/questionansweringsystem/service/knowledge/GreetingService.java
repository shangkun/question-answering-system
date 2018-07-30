package cn.ken.questionansweringsystem.service.knowledge;

import cn.ken.questionansweringsystem.model.knowledge.Greeting;
import cn.ken.questionansweringsystem.model.knowledge.GreetingAnswer;
import cn.ken.questionansweringsystem.model.knowledge.GreetingExtensionQuestion;
import cn.ken.questionansweringsystem.model.knowledge.GreetingRequest;
import cn.ken.questionansweringsystem.model.response.PageData;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/27 <br/>
 * what: 寒暄知识 <br/>
 */
public interface GreetingService {

    public Greeting getGreeting(String greetingId);

    public String add(GreetingRequest request) throws Exception;

    public String batchAdd(List<GreetingRequest> greetingRequestList) throws Exception;

    public boolean isRepeat(GreetingRequest request) throws Exception;

    public boolean isGreetingExtensionQuestionRepeat(GreetingRequest request) throws Exception;

    public String delete(String id) throws Exception;

    public String deleteByIdList(List<String> list) throws Exception;

    public String update(GreetingRequest request) throws Exception;

    public PageData get(GreetingRequest request) throws Exception;

    public List<Greeting> getGreeting(GreetingRequest request) throws Exception;

    public List<GreetingExtensionQuestion> getGreetingExtensionQuestion() throws Exception;

    public List<GreetingAnswer> getGreetingAnswer() throws Exception;
}
