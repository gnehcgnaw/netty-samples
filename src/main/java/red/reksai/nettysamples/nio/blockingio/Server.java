package red.reksai.nettysamples.nio.blockingio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 服务端
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-26 10:03
 * @since :
 */
@SuppressWarnings("all")
public class Server {
    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = null ;
        SocketChannel socketChannel = null ;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8888));
            socketChannel= serverSocketChannel.accept();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while(socketChannel.read(byteBuffer)!=-1){
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array(),0,byteBuffer.limit()));
                byteBuffer.clear();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
