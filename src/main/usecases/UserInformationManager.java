package main.usecases;

import main.entities.User;

public class UserInformationManager {

    User currentUser;

    public UserInformationManager(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getType() {
        return null;
    }
}
