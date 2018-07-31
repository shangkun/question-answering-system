package cn.ken.question.answering.system.model.statistics;

/**
 * author: shangkun <br/>
 * date: 2018/7/30 <br/>
 * what: KnowledgeRankingRequest <br/>
 */
public class KnowledgeRankingRequest extends KnowledgeRanking{

    private Integer index;

    private Integer pageSize;

    private Integer channelId;

    private String startTime;

    private String endTime;

    public KnowledgeRankingRequest() {
    }

    public KnowledgeRankingRequest(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public KnowledgeRankingRequest(String knowledgeId, String time, Integer accessNumber, String startTime, String endTime) {
        super(knowledgeId, time, accessNumber);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
