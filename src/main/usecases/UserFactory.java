package main.usecases;

import main.entities.User;

/**
 * The UserFactory is factory design pattern to create an object of user depends on the type of user.
 *
 * @author Leyi(Amanda) Wang
 * @version 1.0
 * @since 2020-11-02
 */
public class UserFactory {

    /**
     * Create a user with username and password and type.
     *
     * @param username that is the username of the user
     * @param password that is the password of the user
     * @param userType that is the type of the user which are attendees, organizer and speaker.
     */
    public User getUser(String username, String password, String userType) {
        if (userType == null) {
            return null;
        }
        if (userType.equalsIgnoreCase("Attendee")) {
            return new User(username, password, "Attendee");

        } else if (userType.equalsIgnoreCase("Organizer")) {
            return new User(username, password, "Organizer");

        } else if (userType.equalsIgnoreCase("Speaker")) {
            return new User(username, password, "Organizer");
        } else if (userType.equalsIgnoreCase("AdminUser")) {
            return new User(username, password, "AdminUser");
        } else if (userType.equalsIgnoreCase("VipUser")) {
            return new User(username, password, "VipUser"); }

        return null;
    }
}
