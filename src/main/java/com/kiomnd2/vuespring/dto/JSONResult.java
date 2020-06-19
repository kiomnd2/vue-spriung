package com.kiomnd2.vuespring.dto;

public class JSONResult {

    private String result;
    private String message;
    private Object data;


    public static JSONResult success(String message) {
        return new JSONResult("success", message, null);
    }

    public static JSONResult success(Object data) {
        return new JSONResult("success", null, data);
    }
    public static JSONResult success(Object data, String value) {
        return new JSONResult("success", value, data);
    }
    public static JSONResult fail(String message) {
        return new JSONResult("fail", message, null);
    }


    private JSONResult(String result, String message, Object data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public JSONResult() {
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JSONResult{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
