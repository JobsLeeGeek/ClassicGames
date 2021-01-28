package org.jobsl.cgames.net;

import io.netty.channel.Channel;
import org.jobsl.cgames.net.Handler;
import org.jobsl.cgames.net.msg.Message;

public class Myhandler extends Handler {
    @Override
    public void send(Channel channel, Message msg) {
        channel.writeAndFlush(msg);
    }

    @Override
    public void rec(Message msg) {
        System.out.println("client_rec=" + msg.getMsg());
    }
}
