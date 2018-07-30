package cn.ken.question.answering.system.model.response;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 统一返回类 <br/>
 */
public class Response<T> {

    private int statusCode;

    private String message;

    private String info;

    private T data;

    public Response() {
    }

    public Response(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public Response(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public Response(int statusCode, String message, T data,String info) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.info = info;
    }

    public Response(int statusCode, String message,String info) {
        this.statusCode = statusCode;
        this.message = message;
        this.info = info;
    }

    public static Response SUCCESS(){
        Response response = new Response(200,"请求成功");
        return response;
    }

    public static Response SUCCESS(String info){
        Response response = new Response(200,"请求成功",info);
        return response;
    }

    public static Response FAIL(String info){
        Response response = new Response(500,"请求失败",info);
        return response;
    }


    public static Response FAIL(){
        Response response = new Response(500,"请求失败");
        return response;
    }

    public static Response SUCCESS(Object data){
        Response response = new Response(200,"请求成功",data);
        return response;
    }

    public static Response SUCCESS(Object data,String info){
        Response response = new Response(200,"请求成功",data,info);
        return response;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
