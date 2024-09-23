package org.example;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Поехали");
        ClientSocketHandler.putHandlers();
        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        executor.submit(ClientSocketHandler::currentThread);
        try {
            Server.startServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}