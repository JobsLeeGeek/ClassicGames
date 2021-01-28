package org.jobsl.cgames.cchess.net;

import com.alibaba.fastjson.JSONObject;
import org.jobsl.cgames.net.Client;
import org.jobsl.cgames.net.Handler;
import org.jobsl.cgames.net.msg.Message;
import org.jobsl.cgames.net.msg.MessageCode;
import org.jobsl.cgames.net.msg.MessageSign;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class ChessNetBattle {
    private String host;
    private int port = 9191;

    private Handler handler;
    private Client client;

    public void connect() {
        this.handler = new ChessHandler();
        this.client = new Client(this.host, this.port, handler);
        new Thread(() -> { client.run(); }).start();
    }

    public void sendMessage(MessageCode code, ChessMessage chessMsg) {
        Message message = new Message();
        message.setCode(code);
        message.setTime(System.currentTimeMillis());
        message.setMsg(JSONObject.toJSONString(chessMsg));
        try {
            message.setSign(MessageSign.sign(message));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        this.handler.send(this.client.getChannel(), message);
    }

    public void disconnect() {

    }
}
