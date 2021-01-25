package org.jobsl.cgames.net.common.msg;

import io.netty.channel.Channel;

public interface MessageHandler {
    void send(Channel channel, Message msg);

    Message rec(String msg);
}
