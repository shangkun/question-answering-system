package cn.ken.question.answering.system.model.log;

import java.util.Date;
/**
 * author: shangkun <br/>
 * date: 2018/7/30 <br/>
 * what: LogQaEvaluate <br/>
 */
public class LogQaEvaluate {
    private String id;

    private String qaId;

    private Date createTime;

    private Integer evaluate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getQaId() {
        return qaId;
    }

    public void setQaId(String qaId) {
        this.qaId = qaId == null ? null : qaId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Integer evaluate) {
        this.evaluate = evaluate;
    }
}