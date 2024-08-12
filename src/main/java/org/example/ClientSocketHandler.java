package org.example;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ClientSocketHandler extends Thread {
    private static final Map<String, Handler> mapGet = new HashMap<>();

    private final Socket socket; // сокет, через который сервер общается с клиентом,

    public ClientSocketHandler(Socket socket) throws IOException {
        this.socket = socket;
        start();
    }

    public static void putHandlers() {
        mapGet.put("/classic.html", (request, responseStream) -> {
            String path = request.getPath();
            var filePath = Path.of(".", "public", path);
            String mimeType;
            try {
                mimeType = Files.probeContentType(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // special case for classic
            if (path.equals("/classic.html")) {
                String template;
                try {
                    template = Files.readString(filePath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                var content = template.replace(
                        "{time}",
                        LocalDateTime.now().toString()
                ).getBytes();
                try {
                    responseStream.write((
                            "HTTP/1.1 200 OK\r\n" +
                                    "Content-Type: " + mimeType + "\r\n" +
                                    "Content-Length: " + content.length + "\r\n" +
                                    "Connection: close\r\n" +
                                    "\r\n"
                    ).getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    responseStream.write(content);
                    responseStream.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }

    public void run() {
        while (true) {
            try (
                    final var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    final var out = new BufferedOutputStream(socket.getOutputStream())
            ) {
                // read only request line for simplicity
                // must be in form GET /path HTTP/1.1
                final var requestLine = in.readLine();
                System.out.println("requestLine =" + requestLine);
                final var parts = requestLine.split(" ");
                if (parts.length != 3) continue;
                if (!mapGet.containsKey(parts[1])) {
                    out.write(("HTTP/1.1 404 Not Found\r\n").getBytes());
                    out.flush();
                    continue;
                }
                Request request = Request.getRequest(parts);
                Handler handler;
                synchronized (mapGet) {
                    handler = mapGet.get(request.getPath());
                }
                System.out.println("request.getPath() = " + request.getPath());
                handler.handle(request, out);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


}




