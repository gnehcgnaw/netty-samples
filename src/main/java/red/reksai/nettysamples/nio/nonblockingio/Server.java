package red.reksai.nettysamples.nio.nonblockingio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-26 10:45
 * @since :
 */
@SuppressWarnings("all")
public class Server {
    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = null ;
        try {
            //获取通道
            serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.bind(new InetSocketAddress(8888));

            serverSocketChannel.configureBlocking(false);

            //选择器
            Selector selector = Selector.open();

            SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (selector.select()>0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey currentSelectionKey = iterator.next();

                    if (currentSelectionKey.isAcceptable()){

                        SocketChannel socketChannel = serverSocketChannel.accept();

                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ);

                    }
                    if (currentSelectionKey.isReadable()){
                        SocketChannel socketChannel = (SocketChannel)currentSelectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int lenth = 0;
                        while ((lenth = socketChannel.read(byteBuffer))>0){
                            byteBuffer.flip();
                            System.out.println(new String(byteBuffer.array(),0,lenth));
                            byteBuffer.clear();
                        }
                    }
                    iterator.remove();
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
