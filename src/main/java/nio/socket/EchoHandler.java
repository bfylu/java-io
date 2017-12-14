package nio.socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * SocketServer 服务端业务处理类
 *
 * @author bfy
 * @version 1.0.0
 */
public class EchoHandler implements Runnable {

    private SocketChannel socketChannel;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public EchoHandler(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        //读取客户端传输的数据,并原样写入返回给客户端
        try {
            while (socketChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                if (byteBuffer.hasRemaining()) {
                    byteBuffer.compact();
                } else {
                    byteBuffer.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
