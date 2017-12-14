package bio.socket;

import java.io.*;
import java.net.Socket;

/**
 * 服务端处理业务逻辑
 *
 * @author bfy
 * @version 1.0.0
 */
public class BIOEchoServerHandler implements Runnable{

    private Socket socket;
    public BIOEchoServerHandler(Socket socket){
        this.socket = socket;
    }

    public void run(){
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            //通过Socket对象的getInputStream()与getOutputStream()方法获得与客户端通信的输入流与输出流
            bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));

            while (true) {
                //获取客户端的数据
                String line = bufferedReader.readLine();
                if (line == null){
                    break;
                }
                //将客户端获取到的数据
                bufferedWriter.write(line + "bfy\n");
                bufferedWriter.flush();
            }
        } catch (Exception e) {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (bufferedWriter != null) {
                try {
                    this.socket.close();
                } catch (IOException e3){
                    e3.printStackTrace();
                }
            }
        }

    }
}
