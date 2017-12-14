package bio;

import java.io.*;

/**
 * OutputStreamWriter/InputStreamReader
 *
 * @author bfy
 * @version 1.0.0
 */
public class PutStreamRW {

    public static void main(String[] args) throws IOException {
        //创建文件字节输入流
        FileInputStream fileInputStream = new FileInputStream("/home/bfy/IdeaProjects/java-io/src/main/resources/src.txt");
        //利用桥梁InputStreamReader将文件字节输入流inputStream转换成字符输入流
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"utf-8");
        //利用BufferedReader包装字符输入流inputStreamReader,提高性能
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        //创建文件字节输出流
        FileOutputStream fileOutputStream = new FileOutputStream("target.txt");
        //利用桥梁OutputStreamWriter将文件字节输出流OutputStream转换成字符输出流
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        //利用BufferedWriter包装字符输出流outputStreamWriter,提高性能
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        //将文件src.txt 文本内容写入target.txt
        String line = null;
        while ((line = bufferedReader.readLine()) != null){
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        outputStreamWriter.close();
        fileOutputStream.close();
        bufferedReader.close();
        inputStreamReader.close();
        fileInputStream.close();

    }

}
