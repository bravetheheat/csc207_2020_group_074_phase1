package main.controllers;

import main.presenters.AttendeeScreen;
import main.usecases.ContactsManager;
import main.usecases.MessageManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

/**
 * The AttendeeScreenController is a controller class that tells AttendeeScreen what to display, receives input from
 * AttendeeScreen, and tells the ProgramController what screen to go next.
 *
 * @author Yi Tao Li
 * @version 1.0
 * @since 2020-11-11
 */
public class AttendeeScreenController {

    /**
     * Constructor of an AttendeeScreenController.
     */
    public AttendeeScreenController() {}

    /**
     * Checks valid input from attendee and tells ProgramController what screen to go next.
     */
    public String run() {
        AttendeeScreen attendeeScreen = new AttendeeScreen();
        String[] options = {"all events", "registered events", "contacts", "messages"};
        List<String> prompts = (Arrays.asList(options));
        attendeeScreen.prompt();
        Scanner sc = new Scanner(System.in);
        String next = sc.next();
        while (!prompts.contains(next.toLowerCase())){
            attendeeScreen.prompt2(next);
            attendeeScreen.prompt();
            next = sc.next();
        }
        return next.toLowerCase();
    }
}
