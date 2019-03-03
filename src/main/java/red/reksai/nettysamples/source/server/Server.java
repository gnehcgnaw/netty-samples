package red.reksai.nettysamples.source.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;

/**
 * @author gnehcgnaw
 */
public final class Server {

    public static void main(String[] args) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            //配置线程
            b.group(bossGroup, workerGroup)
                    //设置ServerSocketChannel
                    .channel(NioServerSocketChannel.class)
                    //设置客户端属性
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    //绑定客户端属性
                    .childAttr(AttributeKey.newInstance("childAttr"), "childAttrValue")
                    //服务端的逻辑
                    .handler(new ServerHandler())
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            //配置hanlder，数据流的读写和处理逻辑
                            //ch.pipeline().addLast(new AuthHandler());
                            //..

                        }
                    });

            ChannelFuture f = b.bind(8888).sync();

            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}