package main.controllers;

import main.entities.Event;
import main.usecases.ChatRoomManager;
import main.usecases.ContactsManager;
import main.usecases.MessageManager;
import main.controllers.EventController;
import main.usecases.EventsManager;
import java.time.LocalDateTime;
import main.usecases.EventBuilder

import java.util.*;

/**
 * Apart from the responsibilities listed in the UserController, the OrganizerController handles advance actions
 * which could be done by an organizer: checking and managing events, Send messages to all Speakers or one
 * specific speaker, all attendees or one specific attendees
 * Creating speaker accounts, and creating rooms.
 *
 * @author Ruoming Ren
 * @version 1.1
 * @since 2020-11-10
 */

public class OrganizerController extends UserController{

    

    /**
     * Constructor of OrganizerController for a logged in user.
     *
     * @param programController pre-defined programController
     */
    public OrganizerController(ProgramController programController) {
        super(programController);
    }

    /**
     * Build an event by giving the title,
     *
     * @param programController pre-defined programController
     */
    public boolean createEvent(){


    }

    public boolean updateTime(String eventTitle, LocalDateTime time){

    }

    public String getEventInfo(String eventTitle){

    }





}
