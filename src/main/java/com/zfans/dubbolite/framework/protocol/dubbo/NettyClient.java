package com.zfans.dubbolite.framework.protocol.dubbo;

import com.zfans.dubbolite.framework.Invocation;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Zfans
 * @DateTime 2021/6/26 11:11
 */
public class NettyClient {

    public NettyClientHandler client = null;

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public void start(String hostName, Integer port) {

        client = new NettyClientHandler();

        Bootstrap b = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers
                                .weakCachingConcurrentResolver(this.getClass()
                                        .getClassLoader())));
                        pipeline.addLast("encoder", new ObjectEncoder());
                        pipeline.addLast("handler", client);
                    }
                });

        try {
            b.connect(hostName, port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Object send(String hostName, Integer port, Invocation invocation) {

        if (client == null) {
            start(hostName, port);
        }

        client.setInvocation(invocation);

        try {
            return executorService.submit(client).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

}
