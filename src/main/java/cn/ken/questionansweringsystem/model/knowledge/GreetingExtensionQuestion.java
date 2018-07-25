package cn.ken.questionansweringsystem.model.knowledge;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 寒暄扩展问 <br/>
 */
public class GreetingExtensionQuestion {

    private String id;

    private String title;

    private String greetingId;

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

    public String getGreetingId() {
        return greetingId;
    }

    public void setGreetingId(String greetingId) {
        this.greetingId = greetingId;
    }
}