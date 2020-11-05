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
    private static ArrayList<User> registeredUser;

    /**
     * Verify the authentication of new user.
     *
     * @param newUser that should be verified authentication.
     */
    public boolean authenticateUser(User newUser){

        return false;
    }

    /**
     * Remove a user to the list of registered users
     *
     * @param user that should be deleted from the list of registered users
     */
    public void removeUser(User user){
        UUID deletedID = user.getId();
        for (int index = 0; index < registeredUser.size(); index++) {
            if (registeredUser.get(index).getId() == deletedID) {
                registeredUser.remove(registeredUser.get(index));
            }
        }
    }
    /**
     * Add a user to the list of registered users
     *
     * @param user that should be added to the list of registered users
     */
    public void addUser(User user){
        registeredUser.add(user);
    }

    /**
     * Check conflicts for new user to avoid user have same usernames with other
     * registered users
     *
     * @param newUser that is checked for avoiding conflicts.
     */
    public boolean checkConflicts(User newUser){
        //String newUsername = newUser.getName();
        return false;
    }

    /**
     * Return the information of a user.
     *
     */
    public String toString() {
        return "UsersManager{" +
                "ListsofUsers=" + ListsofUsers +
                '}';
    }
}
