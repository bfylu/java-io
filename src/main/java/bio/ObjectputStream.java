package bio;

import model.User;

import java.io.*;

/**
 * ObjectOutputStream/ObjectInputStream:
 *
 * @author bfy
 * @version 1.0.0
 */
public class ObjectputStream {

    public static void main(String[] args) throws IOException,ClassNotFoundException {
        User user = new User();
        user.setEmail("bfyjian@gmail.com");
        user.setName("bfyjian");

        //将User对象序列化到文件
        FileOutputStream fout = new FileOutputStream("user.txt");
        ObjectOutputStream oout = new ObjectOutputStream(fout);
        oout.writeObject(user);
        oout.close();
        fout.close();

        //从user.txt文件中反序列化得到User对象
        FileInputStream fin = new FileInputStream("user.txt");
        ObjectInputStream oin = new ObjectInputStream(fin);
        User newUser = (User) oin.readObject();
        System.out.println("email:" + newUser.getEmail() + " name:" + newUser.getName());
        oin.close();
        fin.close();
    }

}
