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
    private static ArrayList<User> registeredUser;

    /**
     * Verify the authentication of new user with username, password and type of users.
     *
     * @param username that is the user name of the user.
     */
    public boolean authenticateUser(String username, String password, String userType){

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
     * @param username that is the username of the user
     * @param password that is the password of the user
     */
    public void addUser(String username, String password){
        User user = new User(username, password);
        registeredUser.add(user);
    }

    /**
     * Check conflicts for new user to avoid user have same usernames with other
     * registered users
     *
     * @param newUser that is checked for avoiding conflicts.
     */
    public boolean checkConflicts(User newUser){
        String newUsername = newUser.getUsername();
        for (User user : registeredUser) {
            if (user.getUsername().equals(newUsername)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return the information of all Users which include user's UUID, username and password.
     *
     */
    public String toString() {
        StringBuilder usersInfo;
        usersInfo = new StringBuilder("Events: \n");
        for (User user : registeredUser) {
            String userInfo = "User #:" + user.getId() +"\n" + "Username :" + user.getUsername()
                    + "\n" + "Password :" + user.getPassword() + "\n";
            usersInfo.append(userInfo);
        }
        return usersInfo.toString();
    }
}
