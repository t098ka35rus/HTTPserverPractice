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
        try (final var serverSocket = new ServerSocket(9999)) {
            while (true) {
                try (
                        final var socket = serverSocket.accept();
                        final var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        final var out = new BufferedOutputStream(socket.getOutputStream());
                ) {
                    // read only request line for simplicity
                    // must be in form GET /path HTTP/1.1
                    final var requestLine = in.readLine();
                    final var parts = requestLine.split(" ");
                    for (int i = 0; i < parts.length; i++) {
                        System.out.println(parts[i]);
                    }

                    if (parts.length != 3) {
                        continue;
                    }
                Request.putRequest(parts);

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

}
