package nio;


import java.nio.CharBuffer;

/**
 * BufferAPI
 *
 * @author bfy
 * @version
 */
public class BufferAPI {
    public static void main(String[] args) {
        String content = "你好!java Non-Blocking I/O.";
        CharBuffer charBuffer = CharBuffer.allocate(50);
        //将字符串内容写入Buffer
        for (int i = 0;i < content.length(); i++) {
            charBuffer.put(content.charAt(i));
        }
        //反转Buffer,准备读取Buffer内容
        charBuffer.flip();

        //读取Buffer中的数据
        while (charBuffer.hasRemaining()) {
            System.out.print(charBuffer.get());
        }

        //倒回读取之前,准备再次读取
        charBuffer.rewind();
        System.out.println();

        //读取Buffer中的数据
        while (charBuffer.hasRemaining()) {
            System.out.print(charBuffer.get());
        }
        System.out.println();

        //清空Buffer,复位position,Buffer可以再次复用
        charBuffer.clear();
        charBuffer.put('你').put('好').put('!');
        charBuffer.flip();
        //再次读取Buffer中的数据
        while (charBuffer.hasRemaining()) {
            System.out.print(charBuffer.get());
        }
    }


}
