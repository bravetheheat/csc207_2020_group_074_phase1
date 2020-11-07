package main.controllers;

import main.entities.User;
import main.usecases.UsersManager;
import main.usecases.UserFactory;

import java.util.UUID;

public class AuthController {

    private UUID loggedInUser = null;
    private UsersManager usersManager;

    public AuthController() {
        this.usersManager = new UsersManager();
    }

    public AuthController(UsersManager usersManager) {
        this.usersManager = usersManager;
    }

    public boolean login(String username, String password) {
        UUID user = this.usersManager.authenticateUser(username, password);
        this.loggedInUser = user;
        return this.isLoggedIn();

    }

    public boolean isLoggedIn() {
        return (this.loggedInUser != null);
    }

    public UUID fetchLoggedInUser() {
        return this.loggedInUser;
    }

    public boolean registerUser(String username, String password, String userType) {
        return this.usersManager.addUser(username, password, userType);
    }

    /*
    Todo: Return appropriate controllers
     */





}
