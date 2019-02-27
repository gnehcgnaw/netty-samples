package red.reksai.nettysamples.netty.httpserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * 服务端
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-26 22:49
 * @since :
 */
public class Server {
    public static void main(String[] args){
        /*
         * 1. 定义了两个事件循环组：
         *       boosGroup接收连接，将连接发送给worker，由worker来完成连接的处理
         */
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            //2. ServerBootstrap是简化服务端启动的class
            ServerBootstrap serverBootstrap = new ServerBootstrap();
                    //添加组
            serverBootstrap.group(boosGroup,workerGroup)
                    //定义通道
                    .channel(NioServerSocketChannel.class)
                    //定义子处理器
                    .childHandler(new ServerInitializer());

            ChannelFuture channelFuture= serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
