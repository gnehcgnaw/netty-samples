package red.reksai.nettysamples.nio.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 使用直接缓冲区完成文件的物理内存的映射 ${@link TestChannel3#useMapMode(FileChannel, FileChannel)}
 *                                  ${@link  FileChannel#map(FileChannel.MapMode, long, long)}
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-25 14:57
 * @since :
 */
@SuppressWarnings("all")
public class TestChannel3 {
    public static void main(String[] args) {
        FileChannel inputChannel = null ;
        FileChannel outputChannel = null ;
        try {
            OpenOption readOpenOption = StandardOpenOption.READ;
            OpenOption writeOpenOption = StandardOpenOption.WRITE ;
            StandardOpenOption createOpenOption = StandardOpenOption.CREATE;
            Path inputPath = Paths.get(System.getProperty("user.dir"),"src/main/resources/","1.jpg");
            Path outputPath = Paths.get(System.getProperty("user.dir"),"src/main/resources/","123.jpg");

            inputChannel = FileChannel.open(inputPath, readOpenOption);
            outputChannel = FileChannel.open(outputPath, writeOpenOption,createOpenOption ,readOpenOption);

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
        FileChannel.MapMode readOnlyMapMode = FileChannel.MapMode.READ_ONLY;
        long position = 0 ;
        long size = inputFileChannel.size();
        MappedByteBuffer inputMappedByteBuffer = inputFileChannel.map(readOnlyMapMode, position, size);

        FileChannel.MapMode readWriteMapMode = FileChannel.MapMode.READ_WRITE;
        MappedByteBuffer outputMapperByteBuffer = outputFileChannel.map(readWriteMapMode, 0, size);

        byte[] bytes = new byte[inputMappedByteBuffer.limit()];

        inputMappedByteBuffer.get(bytes);
        outputMapperByteBuffer.put(bytes);

    }
}
