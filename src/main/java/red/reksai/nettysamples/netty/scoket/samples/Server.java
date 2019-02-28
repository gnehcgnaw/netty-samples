package red.reksai.nettysamples.netty.scoket.samples;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-27 11:35
 * @since :
 */
public class Server {
    public static void main(String[] args) {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup wokerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup,wokerGroup)
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChatServerScoketInitialize());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            boosGroup.shutdownGracefully();
            wokerGroup.shutdownGracefully();
        }
    }
}
