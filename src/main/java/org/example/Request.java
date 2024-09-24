package org.example;

import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.net.URLEncodedUtils;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class Request {


    private String method;
    private String path;
    private String protocolVersion;
    private String query;



    private boolean queryDetected = false;




    private Request() {
    }// конструктор

    public static Request getRequest(String[] parts) {
        Request request = new Request();
        request.method = parts[0];
        if(parts[1].contains("?")){
            request.queryDetected = true;
            String[] pathAndQuery = parts[1].split("\\?");
            request.path = pathAndQuery[0];
            request.query = "?" + pathAndQuery[1];
        }else request.path = parts[1];
        request.protocolVersion = parts[2];
        return request;
    }


    public boolean isQueryDetected() {
        return queryDetected;
    }
    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }
    public List<NameValuePair> getQueryParams(){
        String s = path + query;
        URI uri = URI.create(s);
        return URLEncodedUtils.parse(uri, StandardCharsets.ISO_8859_1);
    }

    public String getQueryParam(String name) {
        String s = null;
        List<NameValuePair> list = getQueryParams();
        int i = 0;
        for (NameValuePair nameValuePair : list) {
            if(nameValuePair.getName().equals(name)){
               s =  nameValuePair.getValue();
            }
        }
        return s;
    }








}
