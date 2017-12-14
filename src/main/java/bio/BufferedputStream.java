package bio;

import java.io.*;

/**
 * BufferedOutputStream/BufferedInputStream
 * @author bfy
 * @version 1.0.0
 */
public class BufferedputStream {

    public static void main(String[] args) throws IOException {
        FileOutputStream outputStream = null;
        FileInputStream inputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        BufferedInputStream bufferedInputStream = null;

        //定义源文件与目标文件
        File srcFile = new File("/home/bfy/IdeaProjects/java-io/src/main/resources/src.txt");
        File targetFile = new File("/home/bfy/IdeaProjects/java-io/src/main/resources/target.txt");

        try {
            //实例化文件输入流与文件输出流
            inputStream = new FileInputStream(srcFile);
            //缓冲输入流
            bufferedInputStream = new BufferedInputStream(inputStream);

            outputStream = new FileOutputStream(targetFile);
            //缓冲输出流
            bufferedOutputStream = new BufferedOutputStream(outputStream);

            //通过缓冲输入流读取源文件内容，并写入缓冲输出流，最终写入文件
            byte[] buff = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(buff, 0 , buff.length)) != -1){
                bufferedOutputStream.write(buff, 0, len);
            }
            bufferedOutputStream.flush();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
