package org.jobsl.cgames.cchess.net;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;
import org.jobsl.cgames.cchess.Controller;
import org.jobsl.cgames.net.Handler;
import org.jobsl.cgames.net.msg.Message;
import org.jobsl.cgames.net.msg.MessageHandler;

/**
 * @author JobsLee
 */
public class ChessHandler extends Handler implements MessageHandler {
    private Controller controller;

    public ChessHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void send(Channel channel, Message msg) {
        msg.setTime(System.currentTimeMillis());
        msg.setSign(super.sign(msg));
        channel.writeAndFlush(msg);
    }

    @Override
    public void rec(Message msg) {
        String msgJson = msg.getMsg();
        // check sign
        if (StringUtils.isNotBlank(msgJson) && StringUtils.isNotBlank(msg.getSign()) && msg.getSign().equals(super.sign(msg))) {
            // uncode chessmsg
            ChessMessage chessMsg = JSONObject.parseObject(msgJson, ChessMessage.class);
            if (chessMsg != null) {
                // move
                switch (chessMsg.getCommand()) {
                    case MOVE:
                        controller.move(chessMsg.getFromPoint(), chessMsg.getToPoint());
                        break;
                    default: break;
                }
            }
        }
    }
}
