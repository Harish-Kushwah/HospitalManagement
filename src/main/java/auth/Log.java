package auth;

import java.io.*;

public class Log {

    public static void setUserLog(User user) {
        try {
            File file = new File("./logs/user_auth.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream f_out = new FileOutputStream(file);
            ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
            obj_out.writeObject(user);
            f_out.close();
            obj_out.close();
            System.out.println("Login Successfull");
        } catch (IOException exp) {
        }
    }

    public static User getUserLog() {
        try {
            File file = new File("./logs/user_auth.txt");
            FileInputStream f_in = new FileInputStream(file);
            ObjectInputStream obj_in = new ObjectInputStream(f_in);
            Object obj = obj_in.readObject();
            obj_in.close();
            f_in.close();
            if (obj == null) {

                return null;
            }
            User user = (User) obj;
            return user;
        } catch (IOException | ClassNotFoundException exp) {
        }
        return null;
    }

    public static void removeUserLog() {
        setUserLog(null);
        System.out.println("Logout Successfull");

    }
}
