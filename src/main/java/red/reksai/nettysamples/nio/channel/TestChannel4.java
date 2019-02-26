package red.reksai.nettysamples.nio.channel;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 使用直接缓冲区完成文件的物理内存的映射 ${@link TestChannel3#useMapMode(FileChannel, FileChannel)}
 *  *                               ${@link FileChannel#transferFrom(ReadableByteChannel, long, long)}
 *                                  ${@link FileChannel#transferTo(long, long, WritableByteChannel)}
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-25 14:57
 * @since :
 */
@SuppressWarnings("all")
public class TestChannel4 {
    public static void main(String[] args) {
        FileChannel inputChannel = null ;
        FileChannel outputChannel = null ;
        try {

            Path inputPath = Paths.get(System.getProperty("user.dir"),"src/main/resources/","1.jpg");
            Path outputPath = Paths.get(System.getProperty("user.dir"),"src/main/resources/","1234.jpg");
            inputChannel = FileChannel.open(inputPath,StandardOpenOption.READ);
            outputChannel = FileChannel.open(outputPath,StandardOpenOption.WRITE,StandardOpenOption.CREATE);
            useMapMode(inputChannel,outputChannel);
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

    static void useMapMode (FileChannel inputFileChannel, FileChannel outputFileChannel ) throws  Exception{
       inputFileChannel.transferTo(0,inputFileChannel.size(),outputFileChannel);

    }
}
