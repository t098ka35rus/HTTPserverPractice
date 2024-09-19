package org.example;

import java.util.HashMap;
import java.util.Map;

public class Request {

    private String method;
    private String path;
    private String protocolVersion;



    private Request() {
    }// конструктор

    public static Request getRequest(String[] parts) {
        Request request = new Request();
        request.method = parts[0];
        request.path = parts[1];
        request.protocolVersion = parts[2];


        return request;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }
    public Map<String,String> getQueryParams(){
        Map<String, String> queryMap = new HashMap<>();
        return  queryMap;
    }

    public String getQueryParam1(String name) {
        return "";
    }








}
