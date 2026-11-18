package com.gamelathe.client;

import com.gamelathe.client.network.SocketClient;

public class ConnectionTest {

    public static void main(String[] args) {
        SocketClient client = new SocketClient();

        try {
            client.connect();

            String response = client.sendRequest(
                    "{\"action\":\"test\"}"
            );

            System.out.println("SERVER TRA LOI: " + response);

        } catch (Exception e) {
            System.out.println("LOI KET NOI: " + e.getMessage());

        } finally {
            client.disconnect();
        }
    }
}