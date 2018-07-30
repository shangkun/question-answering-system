package cn.ken.questionansweringsystem.service;

import cn.ken.questionansweringsystem.model.knowledge.Greeting;
import cn.ken.questionansweringsystem.model.knowledge.GreetingAnswer;
import cn.ken.questionansweringsystem.model.knowledge.GreetingExtensionQuestion;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/24 <br/>
 * what: BaseService <br/>
 */
public interface BaseService {

    int batchAddGreeting(List<Greeting> greetingList) throws Exception;

    int batchAddGreetingExtensionQuestion(List<GreetingExtensionQuestion> extensionQuestionList) throws Exception;

    int batchAddGreetingAnswer(List<GreetingAnswer> greetingAnswerList) throws Exception;
}
