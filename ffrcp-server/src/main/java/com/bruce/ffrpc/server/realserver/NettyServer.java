package com.bruce.ffrpc.server.realserver;

import com.bruce.ffrpc.server.config.ServerConfig;
import com.bruce.ffrpc.server.handler.ServiceInvokeHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author bruce.ge
 * @Date 2017/8/8
 * @Description
 */
public class NettyServer {

    private static volatile boolean started = false;

    public static void add(ServerConfig config) throws InterruptedException {
        if (!started) {
            EventLoopGroup boosGroup = new NioEventLoopGroup();

            EventLoopGroup workGroup = new NioEventLoopGroup();

            try {
                ServerBootstrap b = new ServerBootstrap();
                b.group(boosGroup, workGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new ServiceInvokeHandler(config));
                            }
                        }).option(ChannelOption.SO_BACKLOG, 128)
                        .childOption(ChannelOption.SO_KEEPALIVE, true);
                // TODO: 2017/8/8 get the default port for user.
                int port = 8992;
                ChannelFuture f = b.bind(port).sync();
                f.channel().closeFuture().sync();
            } finally {
                workGroup.shutdownGracefully();
                boosGroup.shutdownGracefully();
            }
            started = true;
        }
    }

    public static boolean serverStarted() {
        return started;
    }
}
