package main.gateways;

import main.usecases.UsersManager;
import java.io.*;
import java.util.UUID;


public class UserToTxt {
    public UserToTxt(){}

    public void writeToTxt(UsersManager usersManager) {
        try {
            String filePath = "/txtFiles/Users.txt";
            FileWriter userWriter = new FileWriter(new File(filePath));
            BufferedWriter bw = new BufferedWriter(userWriter);
            String header = "Role, Email, Password";
            bw.write(header);
            bw.newLine();

            for (UUID userID : usersManager.getAllUsers()){
                String role = usersManager.fetchRole(userID);
                String email = usersManager.getUsernameFromID(userID);
                String password = usersManager.fetchUser(userID).getPassword();
                String info = role + ", " + email + ", " + password;
                bw.write(info);
                bw.newLine();

            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
