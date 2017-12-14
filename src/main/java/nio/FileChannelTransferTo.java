package nio;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * 使用transferTo方法实现文件的复制
 *
 * @author bfy
 * @version 1.0.0
 */
public class FileChannelTransferTo {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src.txt");
        //从输入流中获取源文件target.txt的通道
        FileChannel fileChannelIn = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("target.txt");
        //从输出流中获取目标文件target.txt的通道
        FileChannel fileChannelOut = fileOutputStream.getChannel();

        //使用transferTo API 将文件src.txt内容写入target.txt
        fileChannelIn.transferTo(0 , fileChannelIn.size(), fileChannelOut);

        //关闭文件流及通道
        fileChannelOut.close();
        fileChannelOut.close();
        fileChannelIn.close();
        fileInputStream.close();
    }

}
