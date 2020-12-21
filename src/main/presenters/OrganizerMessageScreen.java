package main.presenters;

import main.usecases.UsersManager;

import java.util.List;
@Deprecated
public class OrganizerMessageScreen extends AttendeeMessageScreen {

    public OrganizerMessageScreen(UsersManager usersManager, List<String> recipients) {
        super(usersManager, recipients);
    }

    public void prompt() {
        int count = 1;
        System.out.println("Please select a users to message by entering numbers separated by comma. (e.g. 1,3,4,5)");
        System.out.println("Enter \"all\" to message all users.");
        System.out.println("Enter \"attendees\" to message all attendees.");
        System.out.println("Enter \"speakers\" to message all speakers.");
        System.out.println("Here is a list of users you can message:");
        while (count <= this.recipients.size()) {
            System.out.println(count + ". " + this.usersManager.userToString(recipients.get(count - 1)));
            count++;
        }
        System.out.println("Enter 0 to return to the previous screen.");
    }
}
