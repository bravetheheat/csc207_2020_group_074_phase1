package main.controllers;

import main.gateways.CSVGateway;
import main.gateways.Gateway;
import main.screencontrollers.AnonymousScreenController;
import main.screencontrollers.ScreenController;
import main.usecases.InboxManager;
import main.usecases.MessageManager;
import main.usecases.RoomManager;
import main.usecases.UsersManager;

import java.util.ArrayDeque;
import java.util.Deque;

public class ProgramController {
    UsersManager usersManager;
    MessageManager messageManager;
    AuthController authController;
    EventController eventController;
    Deque<ScreenController> screenControllerHistory = new ArrayDeque<>();
    ScreenController currentScreenController;
    InboxManager inboxManager;
    RoomManager roomManager;
    MessageController messageController;
    Gateway gateway = new CSVGateway();

    public ProgramController() {
        this.usersManager = new UsersManager();
        this.messageManager = new MessageManager();
        this.inboxManager = new InboxManager();
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
        this.roomManager.loadRoomsFromGateway(this.gateway);
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

    public EventController getEventController() {
        return this.eventController;
    }

    public InboxManager getInboxManager() {
        return this.inboxManager;
    }

    public RoomManager getRoomManager() {
        return this.roomManager;
    }

    public Gateway getGateway() {
        return this.gateway;
    }


    /**
     * Sets the next "page" of the program and adds the current "page" into a history stack.
     * Does not switch pages. You need to run the next controller's start method for that.
     *
     * @param screenController the next page
     */
    public void setNewScreenController(ScreenController screenController) {
        this.screenControllerHistory.push(this.currentScreenController);
        this.currentScreenController = screenController;

    }

    /**
     * Takes the previous "page" and sets it as the "current page".
     * Does not switch pages. You need to run the next controller's start method for that.
     */
    public void goToPreviousScreenController() {
        ScreenController previousScreenController = this.screenControllerHistory.pop();
        this.currentScreenController = previousScreenController;
    }

    /**
     * Clears the history of pages.
     */
    public void clearScreenHistory() {
        this.screenControllerHistory = new ArrayDeque<>();
    }

    public MessageController getMessageController() {
        return this.messageController;
    }

}
