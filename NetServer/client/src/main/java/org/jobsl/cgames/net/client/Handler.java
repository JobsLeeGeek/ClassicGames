package org.jobsl.cgames.net.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.jobsl.cgames.net.common.msg.Message;
import org.jobsl.cgames.net.common.msg.MessageHandler;

import java.nio.charset.StandardCharsets;

public abstract class Handler extends ChannelInboundHandlerAdapter implements MessageHandler {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[client] channelActive...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("[client] read-msg...");
        ByteBuf byteBuf = (ByteBuf) msg;
        rec(byteBuf.toString(StandardCharsets.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public abstract void send(Channel channel, Message msg);

    @Override
    public abstract Message rec(String msg);
}
