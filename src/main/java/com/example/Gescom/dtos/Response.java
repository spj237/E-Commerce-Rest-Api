package com.example.Gescom.dtos;

public class Response {
    private int statusCode;
    private boolean success;
    private String message;
    private Object result;
    private String error;

    public Response() {}

    public Response(int statusCode, boolean success, String message, Object result, String error) {
        this.statusCode = statusCode;
        this.success = success;
        this.message = message;
        this.result = result;
        this.error = error;
    }

    // Getters & Setters
    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Object getResult() { return result; }
    public void setResult(Object result) { this.result = result; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}
