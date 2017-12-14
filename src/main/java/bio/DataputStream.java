package bio;

import java.io.*;

/**
 * DataOutputStream/DataInputSteam
 *
 * @author bfy
 * @version 1.0.0
 */
public class DataputStream {

    public static void main(String[] args) throws IOException {

        String fileName = "/home/bfy/IdeaProjects/java-io/src/main/resources/data.txt";

        //将Java原生类型数据通过DataOUtputStream写入文件
        FileOutputStream fout = new FileOutputStream(fileName);
        DataOutputStream dos = new DataOutputStream(fout);

        dos.writeInt(2017);
        dos.writeUTF("你好，java blocking I/O !");
        dos.writeBoolean(true);

        dos.close();
        fout.close();

        //使用DataInputStream从文件中按照写入顺序读取java原生类型数据
        FileInputStream fin = new FileInputStream(fileName);
        DataInputStream dis = new DataInputStream(fin);

        System.out.println(dis.readInt());
        System.out.println(dis.readUTF().toString());
        System.out.println(dis.readBoolean());
    }

}
