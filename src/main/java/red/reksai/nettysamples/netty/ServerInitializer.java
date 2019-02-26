package red.reksai.nettysamples.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 *
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-26 23:02
 * @since :
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //netty提供的channelHandler
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        //自定义的handler
        pipeline.addLast("httpServerHandler",new HttpServerHandler());
    }
}
