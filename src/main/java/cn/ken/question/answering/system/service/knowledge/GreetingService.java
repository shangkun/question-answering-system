package cn.ken.question.answering.system.service.knowledge;

import cn.ken.question.answering.system.model.knowledge.GreetingAnswer;
import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.model.knowledge.Greeting;
import cn.ken.question.answering.system.model.knowledge.GreetingExtensionQuestion;
import cn.ken.question.answering.system.model.knowledge.GreetingRequest;

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

    public int count(GreetingRequest request) throws Exception;

    public List<Greeting> getGreeting(GreetingRequest request) throws Exception;

    public List<GreetingExtensionQuestion> getGreetingExtensionQuestion() throws Exception;

    public List<GreetingAnswer> getGreetingAnswer() throws Exception;
}
