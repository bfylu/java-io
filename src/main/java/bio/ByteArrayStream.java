package bio;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author bfy
 * @version 1.0.0
 */
public class ByteArrayStream {

    public static void main(String[] args) throws IOException {
    //将字符串转换成字节数组
    String str = "你好，java Blocking I/O !";
    byte[] inputBytes = str.getBytes(Charset.forName("UTF-8"));

    //将字节数组转换成字节输入流ByteArrayInputStream
    ByteArrayInputStream inputStream = new ByteArrayInputStream(inputBytes);

    //将字节输入流数据写入字节输出流ByteArrayOutputStream
    byte[] bytes = new byte[1024];
    int len = 0;
    ByteArrayOutputStream outputStream =new ByteArrayOutputStream();

    while ((len = inputStream.read(bytes)) != -1) {
        outputStream.write(bytes, 0, len);
    }
    //将字节输出流转换成字符串打印到控制台
        System.out.println(outputStream.toString("utf-8"));
    }
}
