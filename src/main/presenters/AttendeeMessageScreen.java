package main.presenters;

import main.usecases.UsersManager;

import java.util.List;
import java.util.UUID;

/**
 * The UserController is a presenter class that displays attendee options.
 *
 * @author Yi Tao Li
 * @version 1.0
 * @since 2020-11-11
 */
public class AttendeeMessageScreen {

    private List<UUID> users;
    private UsersManager usersManager;
    /**
     * Constructor of an AttendeeMessageScreen.
     */
    public AttendeeMessageScreen(UsersManager usersManager) {
        this.usersManager = usersManager;
        this.users = usersManager.getAllUsers();
    }

    /**
     * Displays messages in User's inbox.
     */
    public void prompt() {
        Integer count = 1;
        System.out.println("Please select a user to message by entering a number. \n" +
                "Here is a list of users you can message:");
        while (count < users.size()) {
            System.out.println(count.toString() + ". " + usersManager.userToString(users.get(count - 1)));
            count++;
        }
    }

    /**
     * Temporary method for not choosing one of the options.
     */
    public void prompt2(String input) {
        System.out.println(input + " was not one of the options.");
    }
}
