package main.presenters;

import main.usecases.UsersManager;

public class OrganizerMessageScreen extends AttendeeMessageScreen {

    public OrganizerMessageScreen(UsersManager usersManager) {
        super(usersManager);
    }

    public void prompt() {
        int count = 1;
        System.out.println("Please select a users to message by entering numbers separated by comma. (e.g. 1,3,4,5) \n"
                + "Enter \"all\" to message all users.\nHere is a list of users you can message:");
        while (count < users.size()) {
            System.out.println(count + ". " + this.usersManager.userToString(users.get(count - 1)));
            count++;
        }
        System.out.println("Enter 0 to return to the previous screen.");
    }
}
