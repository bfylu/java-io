package bio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * PrintWriter
 * @author bfy
 * @version 1.0.0
 */
public class PrintWriterDemo {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("target.txt");

        PrintWriter pw = new PrintWriter(file);
        //将内容格式化输出到文件target.txt
        pw.format("你好,%s %s %s %s", "java", "Blocking", "I/O", "!");
        pw.flush();
        pw.close();
    }
}
