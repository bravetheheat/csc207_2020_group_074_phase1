package main.usecases;

import main.entities.User;

import java.util.*;

/**
 * The UsersManager holds a list of users and modifies info for ...
 *
 * @author Leyi(Amanda) Wang, David Zhao
 * @version 1.0
 * @since 2020-11-02
 */

public class UsersManager {
    private static Map<UUID, User> registeredUsers;

    public UsersManager() {
        registeredUsers = new HashMap<>();
    }

    public UsersManager(List<User> userList) {
        registeredUsers = new HashMap<>();
        for (User user : userList) {
            registeredUsers.put(user.getId(), user);
        }
    }

    private Map<String, UUID> mapUsernameToUUID() {
        Map<String, UUID> usernameToUUID = new HashMap<>();
        for (UUID id : registeredUsers.keySet()) {
            usernameToUUID.put(registeredUsers.get(id).getUsername(), id);
        }
        return usernameToUUID;
    }

    public UUID getIDFromUsername(String username) {
        return mapUsernameToUUID().get(username);
    }


    /**
     * Verify the authentication of new user with username, password and type of users.
     *
     * @param username that is the user name of the user.
     * @param password that is the password of the user.
     * @return check for authentication of user
     */
    public UUID authenticateUser(String username, String password) {
        for (User user : registeredUsers.values()) {
            if (user.getUsername().equals(username) & user.getPassword().equals(password)) {
                return user.getId();
            }
        }
        return null;
    }


    /**
     * Remove a user to the list of registered users
     *
     * @param userId that should be deleted from the list of registered users
     */
    public void removeUser(UUID userId) {
        registeredUsers.remove(userId);
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
     * Fetches the User object associated with the UUID
     *
     * @param userId UUID of User object
     * @return a User object
     */
    public User fetchUser(UUID userId) {
        return registeredUsers.get(userId);
    }

    /**
     * Return the information of all Users which include user's UUID, username and password.
     *
     * @return String representation of registered users
     */
    public String toString() {
        StringBuilder usersInfo;
        usersInfo = new StringBuilder();
        for (User user : registeredUsers.values()) {
            String userInfo = "User #:" + user.getId() + "\n" + "Username :" + user.getUsername()
                    + "\n" + "Password :" + user.getPassword() + "\n";
            usersInfo.append(userInfo);
        }
        return usersInfo.toString();
    }

    public String fetchRole(UUID user) {
        return fetchUser(user).getRole();
    }

    public String userToString(UUID id) {
        return "Username : " + fetchUser(id).getId() + "(" + fetchRole(id) + ")";
    }

    public List<UUID> getAllUsers() {
        ArrayList allUsers = new ArrayList(Arrays.asList(this.registeredUsers.keySet().toArray()));
            return allUsers;
        }
}
