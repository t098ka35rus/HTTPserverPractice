package org.example;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

public class Server {

Map<String, Handler> mapGet = new HashMap<>();


    public void listen (int port){
        try (final var serverSocket = new ServerSocket(port)) {
            while (true) {
                try (
                        final var socket = serverSocket.accept();
                        final var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        final var out = new BufferedOutputStream(socket.getOutputStream());
                ) {
                    // read only request line for simplicity
                    // must be in form GET /path HTTP/1.1
                    final var requestLine = in.readLine();
                    System.out.println(requestLine);
                    final var parts = requestLine.split(" ");
                    System.out.println("parts.length = " + parts.length);
                    System.out.println(parts[0]);
                    System.out.println(parts[1]);
                    System.out.println(parts[2]);

                    if (parts.length != 3) continue;


                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    public void addHandler (String method, String path, Handler handler) {
        if (method == "GET") {

        }
    }
    public void getRequest (String [] parts){
        Request.putRequest(parts);
    }
    public void getResponse () throws InterruptedException {
        Request request = Request.takeRequest();
        String path = request.getPath();
        Handler handler = mapGet.get(path);
        //handler.handle(request, out);


    }

    public void putHandlers (){
        mapGet.put("/classic.html", new Handler() {
            @Override
            public void handle(Request request, BufferedOutputStream responseStream) {

            }
        });
    }



}
