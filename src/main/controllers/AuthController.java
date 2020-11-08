package main.controllers;

import main.entities.Speaker;
import main.entities.User;
import main.usecases.UserInformationManager;
import main.usecases.UsersManager;

import java.util.UUID;

/**
 * The AuthController handles authentication, maintains a record of who is logged in, and
 * returns the appropriate controller
 */

public class AuthController {

    private UUID loggedInUser = null;
    private UsersManager usersManager;

    /**
     * Default constructor for when there is no pre-defined UsersManager
     */
    public AuthController() {
        this.usersManager = new UsersManager();
    }

    /**
     * Constructor for when these is a pre-defined UsersManager
     *
     * @param usersManager pre-defined UsersManager
     */
    public AuthController(UsersManager usersManager) {
        this.usersManager = usersManager;
    }

    /**
     * Set the UsersManager of the AuthController
     *
     * @param usersManager
     */
    public void setUsersManager(UsersManager usersManager) {
        this.usersManager = usersManager;
    }

    /**
     * Logins the user and stores the logged in user's UUID in the private store
     *
     * @param username Username
     * @param password Password
     * @return whether or not the user is logged in
     */
    public boolean login(String username, String password) {
        UUID user = this.usersManager.authenticateUser(username, password);
        this.loggedInUser = user;
        return this.isLoggedIn();
    }

    /**
     * Logs out by resetting the current logged in user
     */
    public void logout() {
        this.loggedInUser = null;
    }

    /**
     * Checks if there is a logged in user
     *
     * @return whether or not the user is logged in
     */
    public boolean isLoggedIn() {
        return (this.loggedInUser != null);
    }

    /**
     * Fetches the current logged in user
     *
     * @return UUID of the current logged in user
     */
    public UUID fetchLoggedInUser() {
        return this.loggedInUser;
    }

    /**
     * Registers a new user
     *
     * @param username Username
     * @param password Password
     * @param userType The type of the User
     * @return whether or not the user was successfully registered
     */
    public boolean registerUser(String username, String password, String userType) {
        return this.usersManager.addUser(username, password, userType);
    }

    public UserController retrieveController() {
        User currentUser = this.usersManager.fetchUser(this.loggedInUser);
        UserInformationManager userInfo = new UserInformationManager(currentUser);
        String userRole = userInfo.getType();

        switch(userRole){
            case "Attendee": return new AttendeeController();
            case "Organizer": return new OrganizerController();
            case "Speaker": return new SpeakerController();
            default: return null;
        }
    }


}
