package bio.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SocketServer
 *
 * @author bfy
 * @version 1.0.0
 */
public class BIOEchoServer {

    //服务端处理业务逻辑线程池
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        int port =8082;
        ServerSocket serverSocket = null;
        try {
            //绑定一个特定的端口创建ServerSocket对象
            serverSocket = new ServerSocket(port);
            Socket socket = null;
            while (true) {
                //使用ServerSocket的accept()方法监听这个端口的请求连接
                socket = serverSocket.accept();
                executor.submit(new BIOEchoServerHandler(socket));
            }
        } finally {
            if (serverSocket != null){
                serverSocket.close();
            }
        }
    }

}
