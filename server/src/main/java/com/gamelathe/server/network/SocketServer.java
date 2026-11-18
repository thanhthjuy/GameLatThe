package com.gamelathe.server.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private static final int PORT = 5000;

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("SERVER DANG CHAY TAI CONG " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                System.out.println("CLIENT DA KET NOI!");

                ClientHandler handler = new ClientHandler(clientSocket);

                Thread thread = new Thread(handler);
                thread.start();
            }

        } catch (IOException e) {
            System.out.println("LOI SERVER: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SocketServer server = new SocketServer();
        server.start();
    }
}