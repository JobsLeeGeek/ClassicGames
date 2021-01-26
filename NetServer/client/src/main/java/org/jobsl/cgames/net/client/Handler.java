package org.jobsl.cgames.net.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.jobsl.cgames.net.common.msg.Message;
import org.jobsl.cgames.net.common.msg.MessageHandler;

public abstract class Handler extends ChannelInboundHandlerAdapter implements MessageHandler {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[client] channelActive...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("[client] read-msg...");
        Message message = (Message) msg;
        rec(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public abstract void send(Channel channel, Message msg);

    @Override
    public abstract void rec(Message msg);
}
