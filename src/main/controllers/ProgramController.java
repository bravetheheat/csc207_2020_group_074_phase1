package main.controllers;

import main.gateways.CSVGateway;
import main.gateways.Gateway;
import main.screencontrollers.AnonymousScreenController;
import main.screencontrollers.ScreenController;
import main.usecases.*;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The main constructor of the application that coordinates functionality and stores Singleton objects
 *
 * @author David Zhao
 */
public class ProgramController {
    UsersManager usersManager;
    EventsManager eventsManager;
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
        this.eventsManager = new EventsManager();
        this.messageManager = new MessageManager();
        this.inboxManager = new InboxManager();
        this.roomManager = new RoomManager();
        this.authController = new AuthController(this, usersManager);
        this.currentScreenController = new AnonymousScreenController(this);
        this.eventController = new EventController(this);
        this.messageController = new MessageController(this);
    }

    public void start() {
        this.loadData();
        this.currentScreenController.start();

    }

    private void loadData() {
        this.usersManager.loadUsersFromGateway(this.gateway);
        this.roomManager.loadRoomsFromGateway(this.gateway);
        this.eventsManager.loadEventsFromGateway(this.gateway);
        this.messageManager.loadMessagesFromGateway(this.gateway);
        this.inboxManager.loadFromGateway(this.gateway);
    }

    private void saveData() {
        this.usersManager.saveUsersToGateway(this.gateway);
        this.roomManager.saveRoomsToGateway(this.gateway);
        this.eventsManager.saveEventsToGateway(this.gateway);
        this.messageManager.saveMessagesToGateway(this.gateway);
        this.inboxManager.saveToGateway(this.gateway);
    }

    /**
     * Starts the next screen controller, if available.
     * If not, then effectively ends the program.
     */
    public void nextScreenController() {

        if (this.currentScreenController != null) {
            this.currentScreenController.start();
        } else {
            // Save data on exit
            saveData();
        }
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

    public MessageController getMessageController() {
        return this.messageController;
    }

    public EventsManager getEventsManager() {
        return this.eventsManager;
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


}
