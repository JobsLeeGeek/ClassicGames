package org.jobsl.cgames.cchess.net;

import com.alibaba.fastjson.JSONObject;
import org.jobsl.cgames.net.Client;
import org.jobsl.cgames.net.Handler;
import org.jobsl.cgames.net.msg.Message;

public class ChessNetBattle {
    private Handler handler;
    private Client client;

    public void connect() {
        this.handler = new ChessHandler();
        this.client = new Client("127.0.0.1", 9191, handler);
        new Thread(() -> { client.run(); }).start();
    }

    public void sendMessage(ChessMessage chessMsg) {
        Message message = new Message();
        message.setMsg(JSONObject.toJSONString(chessMsg));
        this.handler.send(this.client.getChannel(), message);
    }

    public void disconnect() {

    }
}
