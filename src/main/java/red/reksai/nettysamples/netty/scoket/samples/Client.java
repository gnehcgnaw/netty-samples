package red.reksai.nettysamples.netty.scoket.samples;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-28 10:01
 * @since :
 */
public class Client {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChatScoketInitialize());
            Channel channel = bootstrap.connect("127.0.0.1", 8899).sync().channel();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            for ( ; ; ) {
                channel.writeAndFlush(bufferedReader.readLine()+"\n");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
