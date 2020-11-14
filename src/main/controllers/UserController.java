package main.controllers;

import main.entities.Event;
import main.usecases.ContactsManager;
import main.usecases.MessageManager;

import java.util.*;

/**
 * The UserController is an abstract class for controllers of all user types.
 *
 * @author Yi Tao Li
 * @version 1.3
 * @since 2020-11-12
 */
public abstract class UserController {


    protected UUID loggedInUser;
    protected ContactsManager contactsManager;
    protected MessageManager messageManager;
    protected EventController eventController;

    /**
     * Constructor of UserController for a logged in user.
     *
     * @param programController pre-defined programController
     */
    public UserController(ProgramController programController) {
        this.loggedInUser = programController.getAuthController().fetchLoggedInUser();
        this.contactsManager = programController.getContactsManager();
        this.messageManager = programController.getMessageManager();
        this.eventController = programController.getEventController();
    }

    public abstract ArrayList<Event> checkEvents();
}