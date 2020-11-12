package main.controllers;

import main.presenters.MainScreen;
import main.presenters.Screen;
import main.usecases.ChatRoomManager;
import main.usecases.ContactsManager;
import main.usecases.MessageManager;
import main.usecases.UsersManager;

public class ProgramController implements ProgramInterface {
    UsersManager usersManager;
    ContactsManager contactsManager;
    ChatRoomManager chatRoomManager;
    MessageManager messageManager;
    AuthController authController;
    UserController currentController;
    EventController eventController;
    Screen currentScreen;

    public ProgramController() {
        this.usersManager = new UsersManager();
        this.contactsManager = new ContactsManager();
        this.chatRoomManager = new ChatRoomManager();
        this.messageManager = new MessageManager();
        this.authController = new AuthController(usersManager);
        this.currentScreen = new MainScreen(this);
        this.eventController = new EventController();
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

    public MessageManager getMessageManager() { return this.messageManager; }

    public ContactsManager getContactsManager() {
        return this.contactsManager;
    }

    public ChatRoomManager getChatRoomManager() {
        return this.chatRoomManager;
    }

    public MessageManager getMessageManager() {
        return this.messageManager;
    }

    public EventController getEventController() {
        return this.eventController;
    }
}
