package main.controllers;

import main.gateways.CSVGateway;
import main.gateways.Gateway;
import main.screencontrollers.AnonymousScreenController;
import main.screencontrollers.ScreenController;
import main.usecases.*;

public class ProgramController {
    UsersManager usersManager;
    ContactsManager contactsManager;
    MessageManager messageManager;
    AuthController authController;
    EventController eventController;
    ScreenController previousScreenController;
    ScreenController currentScreenController;
    InboxController inboxController;
    InboxManager inboxManager;
    RoomManager roomManager;
    MessageController messageController;
    Gateway gateway = new CSVGateway();

    public ProgramController() {
        this.usersManager = new UsersManager();
        this.contactsManager = new ContactsManager();
        this.messageManager = new MessageManager();
        this.inboxManager = new InboxManager();
        this.inboxController = new InboxController(messageManager, inboxManager, usersManager);
        this.roomManager = new RoomManager();
        this.authController = new AuthController(this, usersManager);
        this.currentScreenController = new AnonymousScreenController(this);
        this.eventController = new EventController();
        this.messageController = new MessageController(this);
    }

    public void start() {
        this.loadData();
        this.currentScreenController.start();

    }

    public void loadData() {
        this.usersManager.loadUsersFromGateway(this.gateway);
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

    public InboxController getInboxController(){ return this.inboxController;}

    public RoomManager getRoomManager() {
        return this.roomManager;
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

    public MessageController getMessageController() {
        return this.messageController;
    }

    public ScreenController getPreviousScreenController() {
        return this.previousScreenController;
    }
}
