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

    public boolean authenticateUser(User newUser){

        return false;
    }

    public void deleteUser(User user){

    }
    public void addUser(User user){

    }

}
