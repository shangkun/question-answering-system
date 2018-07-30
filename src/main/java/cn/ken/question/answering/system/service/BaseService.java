package cn.ken.question.answering.system.service;

import cn.ken.question.answering.system.model.knowledge.GreetingAnswer;
import cn.ken.question.answering.system.model.knowledge.Greeting;
import cn.ken.question.answering.system.model.knowledge.GreetingExtensionQuestion;
import cn.ken.question.answering.system.model.log.LogQa;
import cn.ken.question.answering.system.model.log.LogQaRecommend;
import cn.ken.question.answering.system.model.qa.QuestionAnswer;

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

    int batchAddLogQa(List<QuestionAnswer> logQaList) throws Exception;

    int batchAddLogQaRecommend(List<LogQaRecommend> logQaRecommendList) throws Exception;
}
