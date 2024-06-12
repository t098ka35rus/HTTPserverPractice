package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("Поехали");
        Server server = new Server();
        server.listen(9999);
    }
}