package org.jobsl.cgames.net.msg;

import io.netty.channel.Channel;

public interface MessageHandler {
    void send(Channel channel, Message msg);

    void rec(Message msg);

    String sign(Message msg);
}
