package bio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * PrintStreamDemo
 *
 * @author bfy
 * @version 1.0.0
 */
public class PrintStreamDemo {



    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("");
        PrintStream printStream = new PrintStream(file);

        //将内容写入文件
        printStream.println("你好，java Blocking I/o！");
        printStream.close();
    }
}
