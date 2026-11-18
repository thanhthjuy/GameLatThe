
package com.gamelathe.client.service;

import com.gamelathe.client.network.SocketClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AuthClient {

    public String register(String username, String password) {
        SocketClient client = new SocketClient();

        try {
            client.connect();

            JsonObject request = new JsonObject();
            request.addProperty("action", "register");
            request.addProperty("username", username);
            request.addProperty("password", password);

            String response = client.sendRequest(request.toString());

            JsonObject result = JsonParser.parseString(response)
                    .getAsJsonObject();

            return result.get("message").getAsString();

        } catch (Exception e) {
            return "Không thể kết nối đến Server!";
        } finally {
            client.disconnect();
        }
    }

    public String login(String username, String password) {
        SocketClient client = new SocketClient();

        try {
            client.connect();

            JsonObject request = new JsonObject();
            request.addProperty("action", "login");
            request.addProperty("username", username);
            request.addProperty("password", password);

            String response = client.sendRequest(request.toString());

            JsonObject result = JsonParser.parseString(response)
                    .getAsJsonObject();

            return result.get("message").getAsString();

        } catch (Exception e) {
            return "Không thể kết nối đến Server!";
        } finally {
            client.disconnect();
        }
    }
}
