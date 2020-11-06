package main.usecases;

import main.entities.Attendee;
import main.entities.Organizer;
import main.entities.Speaker;
import main.entities.User;

import java.util.List;
import java.util.UUID;

/**
 * The UsersManager holds a list of users and modifies info for ...
 *
 * @author Leyi(Amanda) Wang
 * @version 1.0
 * @since 2020-11-02
 */

public class UsersManager {
    private static List<User> registeredUser;

    /**
     * Verify the authentication of new user with username, password and type of users.
     *
     * @param username that is the user name of the user.
     * @param password that is the password of the user.
     * @param userType that is the type of the user which are attendees, organizer and speaker.
     * @return check for authentication of user
     */
    public boolean authenticateUser(String username, String password, String userType) {
        for (User user : registeredUser) {
            if (user.getUsername().equals(username) & user.getPassword().equals(password)) {
                if(userType.equals("Attendee") & user instanceof Attendee){ return true; }
                else if(userType.equals("Organizer") & user instanceof Organizer){return true;}
                else if(userType.equals("Speaker") & user instanceof Speaker){return true;}
            }
        }
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
     * @return check if user is added
     */
    public boolean addUser(String username, String password, String userType){
        if (checkConflicts(username)){
            return false;
        }
        UserFactory userFactory = new UserFactory();
        User user = userFactory.getUser(username, password, userType);
        registeredUser.add(user);
        return true;
    }

    /**
     * Check conflicts for new user to avoid user have same usernames with other
     * registered users
     *
     * @param userName that is checked for avoiding conflicts.
     * @return check for conflicts
     */
    public boolean checkConflicts(String userName){
        for (User user : registeredUser) {
            if (user.getUsername().equals(userName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return the information of all Users which include user's UUID, username and password.
     *
     * @return String representation of registered users
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
