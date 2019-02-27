package red.reksai.nettysamples.netty.scoket.samples;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;

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
            .channel(ServerSocketChannel.class)
            .childHandler(new ChannelHandler() {
                @Override
                public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

                }

                @Override
                public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

                }

                @Override
                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

                }
            });

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            boosGroup.shutdownGracefully();
            wokerGroup.shutdownGracefully();
        }
    }
}
