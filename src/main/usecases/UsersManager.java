package main.usecases;
import main.entities.User;
import java.util.*;

/**
 * The UsersManager holds a list of users and modifies info for ...
 *
 * @author Leyi(Amanda) Wang
 * @version 1.0
 * @since 2020-11-02
 */

public class UsersManager {
    private Dictionary<UUID, ArrayList<User>> ListsofUsers;
    private ArrayList<User> registeredUser;

    public ArrayList<User> getRegisteredUser() {
        return registeredUser;
    }

    public boolean authenticateUser(User newUser){

        return false;
    }

    public void deleteUser(User user){
        UUID deletedID = user.getId();

    }
    public void addUser(User user){
        
    }

}
