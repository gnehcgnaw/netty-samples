package red.reksai.nettysamples.nio.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 使用Channel的静态方法获取Channel
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-25 14:57
 * @since :
 */
@SuppressWarnings("all")
public class TestChannel2 {
    public static void main(String[] args) {
        FileChannel inputChannel = null ;
        FileChannel outputChannel = null ;
        try {
            OpenOption readOpenOption = StandardOpenOption.READ;
            OpenOption writeOpenOption = StandardOpenOption.WRITE ;
            StandardOpenOption createOpenOption = StandardOpenOption.CREATE;
            Path inputPath = Paths.get(System.getProperty("user.dir"),"src/main/resources/","1.jpg");
            Path outputPath = Paths.get(System.getProperty("user.dir"),"src/main/resources/","12.jpg");
            inputChannel = FileChannel.open(inputPath, readOpenOption);
            outputChannel = FileChannel.open(outputPath, writeOpenOption,createOpenOption);
            useAllocateDirect(inputChannel,outputChannel);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(outputChannel!=null){
                try {
                    outputChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (inputChannel!=null){
                try {
                    inputChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    static void useAllocateDirect (FileChannel inputFileChannel, FileChannel outputFileChannel) throws  Exception{
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        while (inputFileChannel.read(byteBuffer)!=-1){
            byteBuffer.flip();
            outputFileChannel.write(byteBuffer);
            byteBuffer.clear();
        }
    }
}
