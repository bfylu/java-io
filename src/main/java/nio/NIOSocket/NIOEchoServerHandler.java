package nio.NIOSocket;

import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Selector选择器
 * @author bfy
 * @version 1.0.0
 */
public class NIOEchoServerHandler implements Runnable{

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private volatile boolean stop;

    /**
     * 初始化多路复用器,绑定监听端口
     * @param port
     */
    public NIOEchoServerHandler(int port)  {
        try {
            //连接队列大小
            int backLog = 1024;
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port), backLog);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(key);
                    }catch (Exception e) {
                        if (key.channel() != null) {
                            key.channel().close();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //多路复用器关闭后,所有注册在上面的Channel和Pipe等资源都会自动注册并关闭
        if (selector != null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleInput(SelectionKey key) throws  IOException {
        if (key.isValid()){
            //处理新接入的请求消息
            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.register(selector, SelectionKey.OP_READ);
            }

            //处理数据的读取
            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                //返回值大于0,读到了字节,对字节进行编解码
                if (readBytes > 0) {
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);

                    String body = new String(bytes, "UTF-8");
                    System.out.println("echo content:" + body);

                    doWrite(sc, body);
                    //返回传为-1,链路已经关闭,需要关闭SocketChannel,释放资源
                } else if (readBytes < 0) {
                    key.channel();
                    sc.close();
                    //返回值等于为0,没有读取到字节,属于正常,忽略
                } else {
                    ;//读到0字节,忽略
                }
            }
        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException {
        if (response != null && response.trim().length() >0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writBuffer = ByteBuffer.allocate(bytes.length);
            writBuffer.put(bytes);
            writBuffer.flip();
            channel.write(writBuffer);
        }
    }
}
