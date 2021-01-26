package org.jobsl.cgames;

import io.netty.channel.Channel;
import org.jobsl.cgames.net.client.Handler;
import org.jobsl.cgames.net.common.msg.Message;

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
