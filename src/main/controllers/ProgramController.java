package main.controllers;

import main.screencontrollers.AnonymousScreenController;
import main.screencontrollers.ScreenController;
import main.usecases.ContactsManager;
import main.usecases.InboxManager;
import main.usecases.MessageManager;
import main.usecases.UsersManager;

public class ProgramController {
    UsersManager usersManager;
    ContactsManager contactsManager;
    MessageManager messageManager;
    AuthController authController;
    EventController eventController;
    ScreenController previousScreenController;
    ScreenController currentScreenController;
    InboxManager inboxManager;

    public ProgramController() {
        this.usersManager = new UsersManager();
        this.contactsManager = new ContactsManager();
        this.messageManager = new MessageManager();
        this.inboxManager = new InboxManager();
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

    public EventController getEventController() {
        return this.eventController;
    }

    public InboxManager getInboxManager() {
        return this.inboxManager;
    }

    public ScreenController getCurrentScreenController() {
        return this.currentScreenController;
    }

    public void setCurrentScreenController(ScreenController screenController) {
        this.currentScreenController = screenController;
    }

    public void setPreviousScreenController(ScreenController screenController) {
       this.previousScreenController = screenController;
    }

    public ScreenController getPreviousScreenController() {
        return this.previousScreenController;
    }
}
