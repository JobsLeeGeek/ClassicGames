package org.jobsl.cgames.net;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.jobsl.cgames.net.msg.Message;
import org.jobsl.cgames.net.msg.MessageHandler;
import org.jobsl.cgames.net.utils.Base64Utils;
import org.jobsl.cgames.net.utils.MD5Utils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

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

    @Override
    public String sign(Message msg) {
        try {
            msg.setSign(null);
            return MD5Utils.getEncryptedPwd(Base64Utils.encode(msg.toString().getBytes()), msg.getMsgId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
