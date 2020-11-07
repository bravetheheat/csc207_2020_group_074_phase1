package main.usecases;

import main.entities.Attendee;
import main.entities.Organizer;
import main.entities.Speaker;
import main.entities.User;

/**
 * The UserFactory is factory design pattern to create an object of user depends on the type of user.
 *
 * @author Leyi(Amanda) Wang
 * @version 1.0
 * @since 2020-11-02
 */
public class UserFactory {
    //use getUser method to get object of type user

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
            return new Attendee(username, password);

        } else if (userType.equalsIgnoreCase("Organizer")) {
            return new Organizer(username, password);

        } else if (userType.equalsIgnoreCase("Speaker")) {
            return new Speaker(username, password);
        }

        return null;
    }
}
