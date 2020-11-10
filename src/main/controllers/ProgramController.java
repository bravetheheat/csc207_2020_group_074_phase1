package main.controllers;

import main.presenters.MainScreen;
import main.presenters.Screen;
import main.usecases.ChatRoomManager;
import main.usecases.MessageManager;
import main.usecases.UsersManager;

public class ProgramController implements ProgramInterface{
    UsersManager usersManager;
    AuthController authController;
    UserController currentController;
    Screen currentScreen;

    public ProgramController() {
        this.usersManager = new UsersManager();
        this.authController = new AuthController(usersManager);
        this.currentScreen = new MainScreen(this);
    }

    public void start() {
        this.currentScreen.start();

    }

    public void nextScreen() {
        this.currentScreen.start();
    }

    public void setScreen(Screen screen) {
        this.currentScreen = screen;
    }
    public UserController getCurrentController() {
        return this.currentController;
    }

    public AuthController getAuthController() {
        return this.authController;
    }

    public UsersManager getUsersManager() {
        return this.usersManager;
    }

}
