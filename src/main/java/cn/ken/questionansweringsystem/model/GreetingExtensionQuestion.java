package cn.ken.questionansweringsystem.model;

public class GreetingExtensionQuestion {
    private String id;

    private String title;

    private String greetingId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getGreetingId() {
        return greetingId;
    }

    public void setGreetingId(String greetingId) {
        this.greetingId = greetingId == null ? null : greetingId.trim();
    }
}