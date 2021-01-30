package org.jobsl.cgames.cchess.net;

import com.alibaba.fastjson.JSONObject;
import org.jobsl.cgames.cchess.Controller;
import org.jobsl.cgames.net.Client;
import org.jobsl.cgames.net.Handler;
import org.jobsl.cgames.net.msg.Message;
import org.jobsl.cgames.net.msg.MessageCode;

import java.util.concurrent.TimeUnit;

public class ChessNetBattle {
    private Controller controller;

    private String host = "127.0.0.1";
    private int port = 9191;

    private Handler handler;
    private Client client;

    public ChessNetBattle(Controller controller) {
        this.controller = controller;
    }

    public ChessNetBattle(Controller controller, String host, int port) {
        this.controller = controller;
        this.host = host;
        this.port = port;
    }

    public boolean connect() {
        boolean res = false;
        this.handler = new ChessHandler(controller);
        this.client = new Client(this.host, this.port, handler);
        new Thread(() -> { client.run(); }).start();
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (this.client.getChannel() != null && this.client.getChannel().isOpen()) res = true;
        return res;
    }

    public void sendMessage(MessageCode code, ChessMessage chessMsg) {
        Message message = new Message();
        message.setCode(code);
        chessMsg.setIp(-1L);
        chessMsg.setTime(System.currentTimeMillis());
        chessMsg.setSign("");
        message.setMsg(JSONObject.toJSONString(chessMsg));
        this.handler.send(this.client.getChannel(), message);
    }

    public void disconnect() {
        if (this.client.getChannel() != null) this.client.getChannel().close();
    }
}
