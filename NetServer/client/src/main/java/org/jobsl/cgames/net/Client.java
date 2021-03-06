package org.jobsl.cgames.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class Client {
    private String host;
    private int port;
    private Channel channel;
    private ChannelInboundHandler handler;

    public Client(String host, int port, ChannelInboundHandler handler) {
        this.host = host;
        this.port = port;
        this.handler = handler;
    }

    public void run() {
        EventLoopGroup client = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(client)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ObjectEncoder());
                            socketChannel.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
                            socketChannel.pipeline().addLast(handler);
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            if (channelFuture.isSuccess()) {
                System.out.println("client init success...");
            }

            this.channel = channelFuture.channel();

            channelFuture.channel().closeFuture().sync();
            System.out.println("client close...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.shutdownGracefully();
            System.out.println("client close ok!");
        }
    }

    public Channel getChannel() {
        return channel;
    }
}
