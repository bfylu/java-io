package bio;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

/**
 * CharArrayReader/CharArrayWriter
 *
 * @author bfy
 * @version 1.0.0
 */
public class CharArrayRW {

    public static void main(String[] args) throws IOException {

        //将字符串转换成字符数组
        String content = "你好!java Blocking I/O!";
        //将字符数组转换成字符输入流CharArrayReader
        CharArrayReader charArrayReader = new CharArrayReader(content.toCharArray());
        //将字符输入流数据写入字符输出流CharArrayWriter
        char[] chars = new char[1024];
        int len = 0;
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        while ((len =charArrayReader.read(chars)) != -1){
            charArrayWriter.write(chars, 0 ,len);
        }

        //获取字符串并打印到控制台
        System.out.println(charArrayWriter.toString());
        //获取字符数组并打印到控制台
        char[] charArray = charArrayWriter.toCharArray();
        for (char c : charArray) {
            System.out.println(c);
        }
    }
}
