package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("server started");
        int port = 8089;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    System.out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());
                    out.println("Введите имя пользователя: ");
                    final String login = in.readLine();
                    out.println("Введите пароль: ");
                    final String password = in.readLine();
                    out.println("Введите e-mail: ");
                    final String email = in.readLine();
                    User user = new User(login, email, password);
                    System.out.println(user.getLogin() + " " + user.getPassword() + " " + user.getEmail());
                    out.println(String.format("Пользователь %s %s добавлен. Порт подключения: %d", user.getLogin(), user.getEmail(), clientSocket.getPort()));
                }
            }
        }
    }
}