package main.presenters;


/**
 * The EventsManagementScreen presents events information and management: create and cancel an event;
 * organize the speaker and room; as well as a presenting list of events for users
 *
 * @author Haoze Huang
 * @version 3.4
 * @since 2020-11-11
 */
public class EventsManagementScreen {

    /**
     * Print the screen name for event management screen
     */
    public void printScreenName(){
        System.out.println("Event Management Screen \n ");
    }

    /**
     * Print the available options and functionalities
     */
    public void promptCommand(){
        System.out.println("What would you like to modify, choose one option (number only) from below:");
        System.out.println("0. Back to organizer screen");
        System.out.println("1. Create room");
        System.out.println("2. Create event");
        System.out.println("3. Cancel event");
        System.out.println("4. Modify room");
        System.out.println("5. Modify time");
        System.out.println("6. Modify speaker");
        System.out.println("7. Print out schedule");
    }

    /**
     * Ask for user to input room number to create a specific room
     */
    public void promptCreateRoom(){
        System.out.println("You are creating a room, please enter the room number (integer only):");
    }

    /**
     * Ask for user to input event title when creating an event
     */
    public void promptCreateEvent(){
        System.out.println("You are creating a event, please enter the following information line by line:");
        System.out.println("Enter event title:");
    }

    public void promptRequirement(){
        System.out.println("Enter requirements for your event [Tech, Table, Stage]");
        System.out.println("Enter your input by comma: ");
    }
    /**
     * Print out list of rooms and ask user choose room number
     * @param roomList List of available rooms
     */
    public void promptRoom(String roomList){
        System.out.println("Here are the available rooms");
        System.out.println(roomList);
        System.out.println("Enter room number from the above list (integer only):");
    }

    /**
     * Print out list of speakers and ask user choose index
     * @param speakerList List of available speakers
     */
    public void promptSpeaker(String speakerList){
        System.out.println("Here are the available speakers");
        System.out.println(speakerList);
        System.out.println("Enter speaker index from the above list (Starting from 1):");
    }

    public void promptDuration(){
        System.out.println("Enter duration in minutes: ");
    }

    public void promptCapacity(){
        System.out.println("Enter capacity of the event: ");
    }

    public void promptType(){
        System.out.println("Enter type of the event: Select from [One, Multi]");
        System.out.println("Any other inputs will automatically create default event");
    }

    /**
     * Print out request to get time in certain format
     */
    public void promptTime(){
        System.out.println("Enter time from 9AM to 5PM (yyyy-MM-dd HH:mm):");
    }

    /**
     * Print out request to get event index
     */
    public void promptEvent(){
        System.out.println("Enter event index from the above list (Starting from 1):");
    }

    /**
     * Print out all available events
     * @param info of all the events
     */
    public void printSchedule(String info){
        System.out.println("Here are the available events");
        System.out.println(info);
    }

    /**
     * Print verification showing one modification has been done successfully
     */
    public void printVerification(){
        System.out.println("Successful modification!");
    }

    /**
     * Print request to ask user to input again due to invalid input
     */
    public void printInvalidInput() {
        System.out.println("Modification failed");
        System.out.println("One of the above input is invalid. Try again.");
    }

    /**
     * Print message when there is no corresponding entity for user to input
     */
    public void printErrorMessage(){
        System.out.println("There is no entity of this type in the program yet");
        System.out.println("Program will now back to options");
    }
}
