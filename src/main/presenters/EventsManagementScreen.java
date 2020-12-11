package main.presenters;


import java.util.List;

/**
 * The EventsManagementScreen presents events information and management: create and cancel an event;
 * organize the speaker and room; as well as a presenting list of events for users
 *
 * @author Haoze Huang
 * @version 3.6
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
        System.out.println("2. Edit room");
        System.out.println("3. Create event");
        System.out.println("4. Cancel event");
        System.out.println("5. Modify room");
        System.out.println("6. Modify time");
        System.out.println("7. Modify speaker");
        System.out.println("8. Modify event capacity");
        System.out.println("9. Print out schedule");
    }
    


    /**
     * Ask for user to input room number to create a specific room
     */
    public void promptCreateRoom(){
        System.out.println("You are creating a room, please enter the room number (integer only):");
    }

    /**
     * Prompt user to input the room capacity
     */
    public void promptRoomCapacity(){
        System.out.println("Please enter the capacity for this room.");
    }


    /**
     * Ask for user to input event title when creating an event
     */
    public void promptCreateEvent(){
        System.out.println("You are creating a event, please enter the following information line by line:");
        System.out.println("Enter event title:");
    }

    /**
     * Prompt user to input event requirements when creating an event
     */
    public void promptRequirement(){
        System.out.println("Enter requirements for your event [Tech, Table, Stage]");
        System.out.println("Enter 'None' if there is no requirement for your event");
        System.out.println("Enter your input by comma: ");
    }

    /**
     * Print out list of rooms and ask user choose room number
     * @param roomList List of available rooms
     */
    public void promptRoom(String roomList){
        System.out.println("Here are the available rooms");
        System.out.println(roomList);
        System.out.println("Enter room index from the above list (integer only):");
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

    /**
     * Prompt user to input event duration when creating an event
     */
    public void promptDuration(){
        System.out.println("Enter duration in minutes: ");
    }

    /**
     * Prompt user to input event capacity when creating an event
     */
    public void promptCapacity(){
        System.out.println("Enter capacity of the event: ");
    }

    /**
     * Prompt user to input the type of event when creating an event
     */
    public void promptType(){
        System.out.println("Enter type of the event: Select from [NoSpeakerEvent, OneSpeakerEvent, MultiSpeakerEvent]");
        System.out.println("Any other alphanumeric inputs will automatically create NoSpeakerEvent");
    }

    /**
     * Print out request to get time in certain format
     */
    public void promptTime(){
        System.out.println("Enter time from 9AM to 5PM (yyyy-MM-dd HH:mm):");
    }

    public void promptRoomNum(){
        System.out.println("Enter the room number that you want to edit:");
    }
    /**
     * Print out request to get event index
     */
    public void promptEvent(){
        System.out.println("Enter event index from the above list (Starting from 1):");
    }

    public void promptRoomConstraint(){
        System.out.println("Enter constraint of room [Tech, Table, Stage]");
        System.out.println("Enter 'None' if there is no requirement for your event");
        System.out.println("Enter your input by comma: ");
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

    /**
     * Print message indicating the event has no speaker
     */
    public void printNoSpeakerEventMessage(){
        System.out.println("This is an no speaker event");
    }

    /**
     * Print the speakers of multiple speaker event
     * @param speakers list of speakers id
     */
    public void promptNumberOfSpeaker(List<String> speakers){
        System.out.println("How many speakers are you adding at once[1 to " + speakers.size()+ "]");
    }

    /**
     * Print the options user may want to modify speaker in multiple speaker event
     */
    public void promptModifyMultiSpeaker(){
        System.out.println("This is multiSpeaker Event, add or remove speaker?");
        System.out.println("1. Add Speaker");
        System.out.println("2. RemoveSpeaker");
    }

    /**
     * print a string representation of speaker in the event
     * @param speakerList string representation of speaker in the event
     */
    public void showEventSpeaker(String speakerList){
        System.out.println("Here are the available speakers, enter index");
        System.out.println(speakerList);
    }
}
