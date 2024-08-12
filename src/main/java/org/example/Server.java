package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    public static final int PORT = 8080;

    public static void startServer() throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            LinkedList<ClientSocketHandler> clientSocketsList = new LinkedList<>();
            while (true) {
                Socket socket = server.accept();
                try {
                    clientSocketsList.add(new ClientSocketHandler(socket)); // добавить новое соединенние в список
                    System.out.println(clientSocketsList.getLast());
                } catch (IOException e) {
                    socket.close();
                }
            }
        }
    }
}






