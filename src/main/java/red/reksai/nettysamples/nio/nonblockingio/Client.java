package red.reksai.nettysamples.nio.nonblockingio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-26 10:45
 * @since :
 */
@SuppressWarnings("all")
public class Client {
    public static void main(String[] args) {
        SocketChannel socketChannel = null ;
        try{
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8888));
            socketChannel.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String nextMessage = scanner.next();
                byteBuffer.put((LocalDateTime.now().toString()+"\n"+nextMessage).getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
            }

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
