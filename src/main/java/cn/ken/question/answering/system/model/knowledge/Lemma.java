package cn.ken.question.answering.system.model.knowledge;

/**
 * author: shangkun <br/>
 * date: 2018/7/27 <br/>
 * what: Lemma <br/>
 */
public class Lemma {

    private String lemmaId;
    private String lemmaTitle;
    private String lemmaCroppedTitle;
    private String lemmaDesc;
    private String lemmaUrl;
    private String lemmaFullDesc;

    public String getLemmaId() {
        return lemmaId;
    }

    public void setLemmaId(String lemmaId) {
        this.lemmaId = lemmaId;
    }

    public String getLemmaTitle() {
        return lemmaTitle;
    }

    public void setLemmaTitle(String lemmaTitle) {
        this.lemmaTitle = lemmaTitle;
    }

    public String getLemmaCroppedTitle() {
        return lemmaCroppedTitle;
    }

    public void setLemmaCroppedTitle(String lemmaCroppedTitle) {
        this.lemmaCroppedTitle = lemmaCroppedTitle;
    }

    public String getLemmaDesc() {
        return lemmaDesc;
    }

    public void setLemmaDesc(String lemmaDesc) {
        this.lemmaDesc = lemmaDesc;
    }

    public String getLemmaUrl() {
        return lemmaUrl;
    }

    public void setLemmaUrl(String lemmaUrl) {
        this.lemmaUrl = lemmaUrl;
    }

    public String getLemmaFullDesc() {
        return lemmaFullDesc;
    }

    public void setLemmaFullDesc(String lemmaFullDesc) {
        this.lemmaFullDesc = lemmaFullDesc;
    }
}
