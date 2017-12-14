package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用FileChannel的read与write方法实现文件的复制
 *
 * @author bfy
 * @version 1.0.0
 */
public class FileChannelRW {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src.txt");
        //从输入流中获取源文件src.txt的通道
        FileChannel fileChannelIn = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("target.txt");
        //从输出流中获取目标文件target.txt的通道
        FileChannel fileChannelOut = fileOutputStream.getChannel();

        //文件读取内容Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int bytesRead = fileChannelIn.read(byteBuffer);
        //一次性可能读不完,所以需要循环读取
        while (bytesRead != -1){
            //翻转Buffer,为下面的读取做准备
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                //将读取到的内容写入target.txt
                fileChannelOut.write(byteBuffer);
            }
            //复位Buffer,以便再次复用Buffer
            byteBuffer.clear();
            bytesRead = fileChannelIn.read(byteBuffer);
        }

        //关闭文件流及通道
        fileChannelOut.close();
        fileOutputStream.close();
        fileChannelIn.close();
        fileInputStream.close();
    }
}
