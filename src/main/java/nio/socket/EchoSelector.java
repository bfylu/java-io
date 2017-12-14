package nio.socket;

import java.io.IOException;
import java.nio.channels.Selector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Selector选择器
 *
 * @author bfy
 * @version 1.0.0
 */
public class EchoSelector {

    //执行服务端业务逻辑线程池
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();


    }

}
