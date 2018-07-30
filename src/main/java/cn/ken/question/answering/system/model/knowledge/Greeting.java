package cn.ken.question.answering.system.model.knowledge;

import com.hankcs.hanlp.seg.common.Term;

import java.util.Date;
import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 寒暄知识 <br/>
 */
public class Greeting {

    private String id;

    private String title;

    private Date modifyTime;

    private String modifierId;

    private String modifierName;

    private List<Term> termList;

    private List<GreetingExtensionQuestion> greetingExtensionQuestionList;

    private List<GreetingAnswer> greetingAnswerList;

    public List<Term> getTermList() {
        return termList;
    }

    public void setTermList(List<Term> termList) {
        this.termList = termList;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public List<GreetingExtensionQuestion> getGreetingExtensionQuestionList() {
        return greetingExtensionQuestionList;
    }

    public void setGreetingExtensionQuestionList(List<GreetingExtensionQuestion> greetingExtensionQuestionList) {
        this.greetingExtensionQuestionList = greetingExtensionQuestionList;
    }

    public List<GreetingAnswer> getGreetingAnswerList() {
        return greetingAnswerList;
    }

    public void setGreetingAnswerList(List<GreetingAnswer> greetingAnswerList) {
        this.greetingAnswerList = greetingAnswerList;
    }
}