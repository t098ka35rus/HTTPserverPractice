package org.example;

import java.util.concurrent.ArrayBlockingQueue;

public class Request {
    int requestsBuffer = 10;
    ArrayBlockingQueue<Request> requests = new ArrayBlockingQueue<>(requestsBuffer);

    private Request (){} // конструктор
    public String getMethod() {
        return method;
    }

    private String method;

    public String getPath() {
        return path;
    }

    private String path;
    private String protocolVersion;

    public static
     


}
