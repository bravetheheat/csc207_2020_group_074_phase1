package main.presenters;


/**
 * The EventsManagementScreen presents events information and management: create and cancel an event;
 * organize the speaker and room; as well as a presenting list of events for users
 *
 * @author Haoze Huang
 * @version 1.0
 * @since 2020-11-11
 */
public class EventsManagementScreen {

    public void printScreenName(){
        System.out.println("Event Management Screen \n ");
    }

    public void promptCommand(){
        System.out.println("What would you like to modify, choose one option (number only) from below:");
        System.out.println("1. Create event");
        System.out.println("2. Cancel event");
        System.out.println("3. Modify event info");
        System.out.println("4. Modify speaker");
        System.out.println("5. Print out single event info");
        System.out.println("6. Print out schedule");
        System.out.println("7. Exit the screen");
    }

    public void promptCreateEvent(){
        System.out.println("You are creating a event, please enter the following information line by line:");
        System.out.println("Line 1 -- Enter event title:");
        System.out.println("Line 2 -- Enter room Id:");
        System.out.println("Line 3 -- Enter speaker Id:");
        System.out.println("Line 4 -- Enter time (yyyy-MM-dd HH:mm):");
    }

    public void promptModifyEvent(){
        System.out.println("Line 1 -- Enter new room:");
        System.out.println("Line 2 -- Enter new time (yyyy-MM-dd HH:mm):");
    }

    public void promptEvent(){
        System.out.println("Which event (enter event id):");
    }

    public void promptSpeaker(){
        System.out.println("Line 1 -- Which speaker (enter speaker id):");
        System.out.println("Line 2 -- Add or remove speaker, choose one option (number only) from below:");
        System.out.println("1. Add speaker");
        System.out.println("2. Remove speaker");
    }

    public void printSchedule(String info){
        System.out.println(info);
    }

    public void printVerification(){
        System.out.println("Successful modification!");
    }

    public void printInvalidInput() {
        System.out.println("Invalid input. Try again.");
    }
}
