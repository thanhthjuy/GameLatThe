
package com.gamelathe.server.network;

import com.gamelathe.server.model.User;
import com.gamelathe.server.service.AuthService;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final AuthService authService;
    private final Gson gson;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.authService = new AuthService();
        this.gson = new Gson();
    }

    @Override
    public void run() {
        try (
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream(),
                    StandardCharsets.UTF_8
                )
            );
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    socket.getOutputStream(),
                    StandardCharsets.UTF_8
                )
            )
        ) {
            String request;

            while ((request = reader.readLine()) != null) {

                System.out.println("Client gửi: " + request);

                String response = handleRequest(request);

                writer.write(response);
                writer.newLine();
                writer.flush();
            }

        } catch (IOException e) {
            System.out.println("Client đã ngắt kết nối.");
        }
    }

    private String handleRequest(String request) {

        try {
            JsonObject json = JsonParser.parseString(request)
                    .getAsJsonObject();

            String action = json.get("action").getAsString();
            String username = json.get("username").getAsString();
            String password = json.get("password").getAsString();

            JsonObject response = new JsonObject();

            if (action.equals("register")) {

                String message = authService.register(username, password);

                response.addProperty("status",
                        message.equals("Đăng ký thành công!") ? "success" : "error");

                response.addProperty("message", message);

            } else if (action.equals("login")) {

                User user = authService.login(username, password);

                if (user != null) {
                    response.addProperty("status", "success");
                    response.addProperty("message", "Đăng nhập thành công!");
                    response.addProperty("id", user.getId());
                    response.addProperty("username", user.getUsername());
                } else {
                    response.addProperty("status", "error");
                    response.addProperty("message",
                            "Tên đăng nhập hoặc mật khẩu không đúng!");
                }

            } else {
                response.addProperty("status", "error");
                response.addProperty("message", "Yêu cầu không hợp lệ!");
            }

            return gson.toJson(response);

        } catch (SQLException e) {
            e.printStackTrace();

            JsonObject response = new JsonObject();
            response.addProperty("status", "error");
            response.addProperty("message", "Lỗi cơ sở dữ liệu!");

            return gson.toJson(response);

        } catch (Exception e) {
            JsonObject response = new JsonObject();
            response.addProperty("status", "error");
            response.addProperty("message", "Dữ liệu gửi lên không hợp lệ!");

            return gson.toJson(response);
        }
    }
}
