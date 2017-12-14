package bio;

import java.io.*;

/**
 * BufferedFileRreader/BufferedFileWriter
 *
 * @author bfy
 * @version 1.0.0
 */
public class BufferedFileRW {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("/home/bfy/IdeaProjects/java-io/src/main/resources/src.txt");
        //读取的数据放入缓冲区
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        FileWriter fileWriter = new FileWriter("target.txt");
        //写入的数据放入缓冲区
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        //利用BufferedReader/BufferedWriter实现逐行读写,提高I/O性能
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        fileWriter.close();
        bufferedReader.close();
        fileReader.close();
    }
}
