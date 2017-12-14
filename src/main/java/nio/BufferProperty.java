package nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Buffer属性值
 * @author bfy
 * @version 1.0.0
 */
public class BufferProperty {
    public static void main(String[] args){
        Buffer buffer = ByteBuffer.allocate(10);
        System.out.println("Capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("Position:" + buffer.position());
        System.out.println("Remaining:" + buffer.remaining());
        System.out.println("设置buffer的limit属性为6");
        buffer.limit(6);
        System.out.println("limit" + buffer.limit());
        System.out.println("Position: " + buffer.position());
        System.out.println("Remaining:" + buffer.remaining());
        buffer.position(2);
        System.out.println("Position:" + buffer.position());
        System.out.println("Remaining+" + buffer.remaining());
        System.out.println(buffer);
    }

}
