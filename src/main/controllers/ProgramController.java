package main.controllers;

import main.screencontrollers.AnonymousScreenController;
import main.screencontrollers.*;
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
    ScreenController currentScreenController;

    public ProgramController() {
        this.usersManager = new UsersManager();
        this.contactsManager = new ContactsManager();
        this.chatRoomManager = new ChatRoomManager();
        this.messageManager = new MessageManager();
        this.authController = new AuthController(this, usersManager);
        this.currentScreenController = new AnonymousScreenController(this);
        this.eventController = new EventController();
    }

    public void start() {
        this.currentScreenController.start();

    }

    public void nextScreenController() {
        this.currentScreenController.start();
    }

    public void setCurrentScreenController(ScreenController screenController) {
        this.currentScreenController = screenController;
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

    public MessageManager getMessageManager() {
        return this.messageManager;
    }

    public ContactsManager getContactsManager() {
        return this.contactsManager;
    }

    public ChatRoomManager getChatRoomManager() {
        return this.chatRoomManager;
    }

    public EventController getEventController() {
        return this.eventController;
    }

    public ScreenController getCurrentScreenController() {
        return this.currentScreenController;
    }
}
