package bio;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * FileWriter/FileReader
 *
 * @author bfy
 * @version 1.0.0
 */
public class FileRW {

    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("/home/bfy/IdeaProjects/java-io/src/main/resources/src.txt");
        FileWriter fw = new FileWriter("target.txt");
        int len;
        while ((len = fr.read() ) != -1){
            fw.write(len);
        }
        fw.close();
        fr.close();
    }
}
