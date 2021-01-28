package org.jobsl.cgames.net;

import org.jobsl.cgames.net.msg.Message;

import java.util.Scanner;

public class ClientTest2 {
    public static void main(String[] args) {
        Handler handler = new Myhandler();
        Client client = new Client("127.0.0.1", 9191, handler);
        new Thread(() -> { client.run(); }).start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            Message message = new Message();
            message.setMsg(scanner.nextLine());
            handler.send(client.getChannel(), message);
        }
    }
}
