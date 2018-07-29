package cn.ken.questionansweringsystem.model.knowledge;

import com.hankcs.hanlp.seg.common.Term;

import java.util.Date;
import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 知识 <br/>
 */
public class Knowledge {

    private String id;

    private String title;

    private String classificationId;

    private String classificationName;

    private Date modifyTime;

    private String modifierId;

    private String modifierName;

    private String teacherId;

    private Integer status;

    private List<ExtensionQuestion> extensionQuestionList;

    private List<Answer> answerList;

    private List<Term> termList;

    public List<Term> getTermList() {
        return termList;
    }

    public void setTermList(List<Term> termList) {
        this.termList = termList;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public List<ExtensionQuestion> getExtensionQuestionList() {
        return extensionQuestionList;
    }

    public void setExtensionQuestionList(List<ExtensionQuestion> extensionQuestionList) {
        this.extensionQuestionList = extensionQuestionList;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
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

    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}