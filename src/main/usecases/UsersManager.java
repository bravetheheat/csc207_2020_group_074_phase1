package main.usecases;

import main.entities.User;
import main.gateways.Gateway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The UsersManager holds a list of users and modifies info for ...
 *
 * @author Leyi(Amanda) Wang, David Zhao
 * @version 1.0
 * @since 2020-11-02
 */

public class UsersManager {
    private Map<String, User> registeredUsers;

    public UsersManager() {
        registeredUsers = new HashMap<>();
    }

    public UsersManager(List<User> userList) {
        registeredUsers = new HashMap<>();
        for (User user : userList) {
            registeredUsers.put(user.getId(), user);
        }
    }

    /**
     * Gets the username for a user with the given user ID
     * @param userId The id of a User
     * @return The specified user's username.
     */
    public String getUsernameFromID(String userId) {
        User user = registeredUsers.get(userId);
        return user.getUsername();
    }

    public String getIDFromUsername(String username) {
        for (User user : registeredUsers.values()) {
            if (user.getUsername().equals(username)) {
                return user.getId();
            }
        }
        return "";
    }


    /**
     * Verify the authentication of new user with username, password and type of users.
     *
     * @param username that is the user name of the user.
     * @param password that is the password of the user.
     * @return check for authentication of user
     */
    public String authenticateUser(String username, String password) {
        for (User user : registeredUsers.values()) {
            if (user.getUsername().equals(username) & user.getPassword().equals(password)) {
                return user.getId();
            }
        }
        return null;
    }


    /**
     * Remove a user by id to the list of registered users
     *
     * @param userId that should be deleted from the list of registered users
     * @return whether user is removed
     */
    public boolean removeUserbyID(String userId) {
        if (registeredUsers.containsKey(userId)){
            registeredUsers.remove(userId);
            return true;
        }
        return false;
    }

    /**
     * Remove a user by username to the list of registered users
     *
     * @param username that should be deleted from the list of registered users
     * @return whether user is removed
     */
    public boolean removeUserbyUsername(String username) {
        for (User user : registeredUsers.values()) {
            if (user.getUsername().equals(username)){
                registeredUsers.remove(user.getId());
                return true;
            }
        }
        return false;
    }

    /**
     * Add a user to the list of registered users
     *
     * @param username that is the username of the user
     * @param password that is the password of the user
     * @return check if user is added
     */
    public boolean addUser(String username, String password, String userType) {

        if (checkConflicts(username)) {
            return false;
        }
        UserFactory userFactory = new UserFactory();
        User user = userFactory.getUser(username, password, userType);
        registeredUsers.put(user.getId(), user);
        return true;
    }

    /**
     * Adds a user to the list of registered users
     *
     * @param user A User object
     */
    public void addUser(User user) {
        registeredUsers.put(user.getId(), user);
    }

    /**
     * Check conflicts for new user to avoid user have same usernames with other
     * registered users
     *
     * @param userName that is checked for avoiding conflicts.
     * @return whether there is a conflict
     */
    public boolean checkConflicts(String userName) {
        for (User user : registeredUsers.values()) {
            if (user.getUsername().equals(userName)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Fetches the User object associated with the String
     *
     * @param userId String of User object
     * @return a User object
     */
    public User fetchUser(String userId) {
        return registeredUsers.get(userId);
    }

    /**
     * Return the information of all Users which include user's String, username and password.
     *
     * @return String representation of registered users
     */
    public String toString() {
        StringBuilder usersInfo;
        usersInfo = new StringBuilder();
        for (User user : registeredUsers.values()) {
            String userInfo = "User #:" + user.getId() + "\n" + "Username: " + user.getUsername()
                    + "\n" + "Password: " + user.getPassword() + "\n" + "User Type: " + user.getType() +"\n";
            usersInfo.append(userInfo);
        }
        return usersInfo.toString();
    }

    /**
     * Returns the type of the user.
     *
     * @param user String of the user in question.
     */
    public String fetchType(String user) {
        return fetchUser(user).getType();
    }

    /**
     * Returns the string representation of a user.
     *
     * @param id String of the user in question
     */
    public String userToString(String id) {
        return "Username : " + fetchUser(id).getUsername() + "(" + fetchType(id) + ")";
    }

    /**
     * Returns a list of String of all users.
     */
    public List<String> getAllUsers() {
        List<String> allUsers = new ArrayList<>();
        allUsers.addAll(registeredUsers.keySet());
        return allUsers;
    }

    public ArrayList<String> allUsersToString() {
        List<String> userIDs = this.getAllUsers();
        ArrayList<String> users = new ArrayList<>();
        for (String id:userIDs) {
            User user = fetchUser(id);
            users.add(user.getType() + ", " + user.getUsername());
        }
        return users;
    }

    public ArrayList<String> selectUsersToString(ArrayList<String> userIDs) {
        ArrayList<String> users = new ArrayList<>();
        for (String id:userIDs) {
            User user = fetchUser(id);
            users.add(user.getType() + ", " + user.getUsername());
        }
        return users;
    }

    /**
     * Loads user data from a specified gateway
     *
     * @param gateway An implementation of the Gateway interface
     */
    public void loadUsersFromGateway(Gateway gateway) {
        registeredUsers = new HashMap<>();
        List<User> loadedUsers = gateway.loadUsers();
        for (User user : loadedUsers) {
            registeredUsers.put(user.getId(), user);
        }
    }

    /**
     * Saves user data to a specified gateway
     *
     * @param gateway An implementation of the Gateway interface
     */
    public void saveUsersToGateway(Gateway gateway) {
        List<User> userList = new ArrayList<>();
        userList.addAll(registeredUsers.values());
        gateway.saveUsers(userList);
    }

    /**
     * a method gives a format version of the users' info
     * @return a list contain users' info
     */
    public ArrayList<String> getUserInfoFormatted() {
        ArrayList<String> listOfUserInfo = new ArrayList<>();
        for (User user : registeredUsers.values()) {
            String userInfo = "User #:" + user.getId() + "\n" + "Username: " + user.getUsername()
                    + "\n" + "Password: " + user.getPassword() + "\n" + "User Type: " + user.getType() +"\n";
            listOfUserInfo.add(userInfo);
        }
        return listOfUserInfo;
    }
}
