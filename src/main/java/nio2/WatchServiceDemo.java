package nio2;

import java.io.IOException;
import java.nio.file.*;

/**
 * @author bfy
 * @version
 */
public class WatchServiceDemo {
    public void watchDir(Path path) throws IOException, InterruptedException {
        try {
            //创建WatchService实例
            WatchService watchService = FileSystems.getDefault().newWatchService();
            //注册WatchService所监听的事件(目录创建,目录修改,目录删除)
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);

            //无限循环获取监听到的事件
            while (true) {
                final WatchKey key = watchService.take();
                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                    final WatchEvent.Kind<?> kind = watchEvent.kind();
                    //忽略OVERFLOW事件
                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }

                    final WatchEvent<Path> pathWatchEvent = (WatchEvent<Path>) watchEvent;
                    final Path fileName = pathWatchEvent.context();
                    //打印事件类型及发生事件的文件名称
                    System.out.println(kind + " : " + fileName );
                }
                //重置key
                boolean valid = key.reset();
                //如果key无效(比如监听的文件被删除),则退出
                if (!valid) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final Path path = Paths.get("/home/bfy");
        WatchServiceDemo watchServiceDemo = new WatchServiceDemo();
        try {
            watchServiceDemo.watchDir(path);
        } catch (IOException | InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
