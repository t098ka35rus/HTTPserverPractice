package org.example;

import java.util.concurrent.ArrayBlockingQueue;

public class Request {
    private static final int requestsBuffer = 10;
    private static ArrayBlockingQueue<Request> requests = new ArrayBlockingQueue<Request>(requestsBuffer);

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

public static void putRequest (String [] parts){
    Request request = new Request();
    request.method = parts [0];
    request.path = parts [1];
    try {
        requests.put(request);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}
     


}
