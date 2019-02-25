package red.reksai.nettysamples.nio.buffer;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-25 00:18
 * @since :
 */

@Slf4j
public class TestBuffer {
    public static void main(String[] args) {
        demo();
    }
    public static  void demo(){
        String string = "abcdef" ;
        //1.分配一个指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //初始化缓冲区
        getBufferMessage("init",byteBuffer);
        //缓冲区存取数据
        byteBuffer.put(string.getBytes(Charset.defaultCharset()));
        //存入数据之后
        getBufferMessage("put data ",byteBuffer);

        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.limit()];
        ByteBuffer getDataBuffer = byteBuffer.get(bytes);

        log.info("data is {}",new String(bytes,0 ,bytes.length));

        getBufferMessage("get data ",byteBuffer);
        byteBuffer.reset();
        getBufferMessage("reset data ",byteBuffer);

        //可重复读数据
        byteBuffer.rewind();
        getBufferMessage("rewind data ",byteBuffer);

        //清空缓冲区,但是缓冲区的数据依然存在，但是这些数据处于被遗忘状态，因为buffer的几个属性恢复到了初始状态
        byteBuffer.clear();
        getBufferMessage("clear buffer ",byteBuffer);
    }

    private static void getBufferMessage(String currentStatus ,ByteBuffer byteBuffer){
       log.info("\n current status is {} " + "\n mark is {} \n position is {} \n limit is {} \n capacity is {}",
                                                              currentStatus,
                                                              byteBuffer.mark(),    //缓冲器标记
                                                              byteBuffer.position(),    //当前位置
                                                              byteBuffer.limit(),   //buffer位置界限
                                                              byteBuffer.capacity());   //buffer的容量
    }
}

