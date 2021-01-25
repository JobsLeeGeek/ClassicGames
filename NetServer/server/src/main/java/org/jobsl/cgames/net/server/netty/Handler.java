package org.jobsl.cgames.net.server.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.jobsl.cgames.net.common.msg.Message;

import java.nio.charset.StandardCharsets;

@Slf4j
public class Handler extends ChannelInboundHandlerAdapter {
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("[server] [{}] channelActive...", ctx.channel().remoteAddress().toString());
        Message message = new Message();
        message.setMsg("[server] hi, you connected server success ...");
        ctx.writeAndFlush(message);
        channels.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("[server] [{}] channelInactive...", ctx.channel().remoteAddress().toString());
        channels.remove(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        String message = byteBuf.toString(StandardCharsets.UTF_8);
        log.info("[server] rec-msg from [{}]:{}", ctx.channel().remoteAddress().toString(), message);
        for (Channel ch : channels) {
            if (!ch.equals(ctx.channel())) ch.writeAndFlush(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
