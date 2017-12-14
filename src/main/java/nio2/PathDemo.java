package nio2;

import java.io.File;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Path工具类使用
 *
 * @author bfy
 * @version 1.0.0
 *
 */
public class PathDemo {
    public static void main(String[] args){
        //通过工具类Paths获取绝对路径Path
        Path path = Paths.get("/home/bfy/IdeaProjects/java-io/src/main/resources/src.txt");
        System.out.println("path1" + path);
        path = Paths.get("./","hoem","bfy","IdeaProjects", "java-io", "src", "main", "resources", "src.txt");
        System.out.println("path2" + path);

        //相对路径Path
        path = Paths.get("src/main/resources/src.txt");
        System.out.println("path3:" + path);

        //通过URI获取Path
        path = Paths.get(URI.create("file:///src/main/resources/src.txt"));
        System.out.println("path4:" + path);

        //通过FileSystems获取Path
        path = FileSystems.getDefault().getPath("src/main/resources/src.txt");

        System.out.println("path5:" + path);

        //Path转成File
        File path_to_file = path.toFile();
        System.out.println("Path to file name:" + path_to_file.getName());

        //Path转成URI
        URI path_to_uri = path.toUri();
        System.out.println("Path to URI:" + path_to_uri);

        //获取绝对路径
        Path path_to_absolute_path = path.toAbsolutePath();
        System.out.println("Path to absolute path:" + path_to_absolute_path);
    }

}
