package cn.ken.questionansweringsystem.model;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 统一返回类 <br/>
 */
public class Response<T> {

    private int statusCode;

    private String message;

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

    public static Response SUCCESS(){
        Response response = new Response(200,"请求成功");
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
