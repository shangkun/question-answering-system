package cn.ken.question.answering.system.model.statistics;

/**
 * author: shangkun <br/>
 * date: 2018/7/30 <br/>
 * what: InfoStatistics <br/>
 */
public class InfoStatistics {

    private String id;

    private String time;

    private Integer lexicon;

    private Integer knowledge;

    private Integer classification;

    private Integer greeting;

    private Integer qa;

    private Integer type;

    public InfoStatistics(Integer lexicon, Integer knowledge, Integer classification, Integer greeting, Integer qa) {
        this.lexicon = lexicon;
        this.knowledge = knowledge;
        this.classification = classification;
        this.greeting = greeting;
        this.qa = qa;
    }

    public InfoStatistics() {
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getLexicon() {
        return lexicon;
    }

    public void setLexicon(Integer lexicon) {
        this.lexicon = lexicon;
    }

    public void setLexiconAccumulation(Integer lexicon) {
        this.lexicon += lexicon;
    }

    public Integer getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(Integer knowledge) {
        this.knowledge = knowledge;
    }

    public void setKnowledgeAccumulation(Integer knowledge) {
        this.knowledge += knowledge;
    }

    public Integer getClassification() {
        return classification;
    }

    public void setClassification(Integer classification) {
        this.classification = classification;
    }

    public void setClassificationAccumulation(Integer classification) {
        this.classification += classification;
    }

    public Integer getGreeting() {
        return greeting;
    }

    public void setGreeting(Integer greeting) {
        this.greeting = greeting;
    }

    public void setGreetingAccumulation(Integer greeting) {
        this.greeting += greeting;
    }

    public Integer getQa() {
        return qa;
    }

    public void setQa(Integer qa) {
        this.qa = qa;
    }

    public void setQaAccumulation(Integer qa) {
        this.qa += qa;
    }
}
