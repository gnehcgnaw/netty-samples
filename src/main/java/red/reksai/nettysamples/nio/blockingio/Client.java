package red.reksai.nettysamples.nio.blockingio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-26 10:09
 * @since :
 */
public class Client {
    public static void main(String[] args) {
        SocketChannel socketChannel = null ;
        try{
            socketChannel= SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1",8888));
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put(LocalDateTime.now().toString().getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (socketChannel!=null){
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
