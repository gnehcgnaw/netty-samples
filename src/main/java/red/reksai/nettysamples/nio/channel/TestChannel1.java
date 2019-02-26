package red.reksai.nettysamples.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用输入输出流${@link java.io.InputStream }${@link java.io.OutputStream },获取Channel${@link java.nio.channels.Channel},
 * 配合${@link java.nio.Buffer}完成数据的传输交换。
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-25 14:30
 * @since :
 */
@SuppressWarnings("all")
public class TestChannel1 {
    public static void main(String[] args){

        FileInputStream fileInputStream = null ;
        FileOutputStream fileOutputStream = null ;
        FileChannel inputStreamChannel = null ;
        FileChannel outputStreamChannel = null ;

        try {
            System.out.println();
            fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/1.jpg");
            fileOutputStream = new FileOutputStream(System.getProperty("user.dir")+"/src/main/resources/2.jpg");
             //创建一个通道
            inputStreamChannel = fileInputStream.getChannel();
            outputStreamChannel= fileOutputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (inputStreamChannel.read(byteBuffer)!=-1){
                byteBuffer.flip();
                outputStreamChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (outputStreamChannel!=null){
                try {
                    outputStreamChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamChannel!=null){
                try {
                    inputStreamChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
