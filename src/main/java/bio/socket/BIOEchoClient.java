package bio.socket;

import java.io.*;
import java.net.Socket;

/**
 * SocketClient
 *
 * @author bfy
 * @version 1.0.0
 */
public class BIOEchoClient {

    public static void main(String[] args) throws IOException {
        int port = 8082;
        String serverIp ="127.0.0.1";
        Socket socket = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            //创建Socket对象,并连接远程主机
            socket = new Socket(serverIp, port);
            //建立连接后,从Socket得到输入流与输出流,可以使用这两个流与服务器之间相互发送数据
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //向服务端发送数据
            bufferedWriter.write("Hello, Block IO.\n");
            bufferedWriter.flush();
            //获取服务端返回的数据
            String echo = bufferedReader.readLine();
            System.out.println("echo:" + echo);
        }finally {
            if (bufferedReader != null) {
                try{
                    bufferedReader.close();
                } catch (IOException e1){
                    e1.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }

    }

}
