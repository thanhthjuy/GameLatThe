package com.gamelathe.client.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketClient {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 5000;

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public void connect() throws IOException {
        socket = new Socket(SERVER_HOST, SERVER_PORT);

        reader = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream(),
                        StandardCharsets.UTF_8
                )
        );

        writer = new BufferedWriter(
                new OutputStreamWriter(
                        socket.getOutputStream(),
                        StandardCharsets.UTF_8
                )
        );

        System.out.println("CLIENT DA KET NOI SERVER!");
    }

    public String sendRequest(String requestJson) throws IOException {
        writer.write(requestJson);
        writer.newLine();
        writer.flush();

        return reader.readLine();
    }

    public void disconnect() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                System.out.println("CLIENT DA NGAT KET NOI!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}