package main.presenters;


/**
 * The EventsManagementScreen presents events information and management: create and cancel an event;
 * organize the speaker and room; as well as a presenting list of events for users
 *
 * @author Haoze Huang
 * @version 3.2
 * @since 2020-11-11
 */
public class EventsManagementScreen {

    public void printScreenName(){
        System.out.println("Event Management Screen \n ");
    }

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

    public void promptCreateRoom(){
        System.out.println("You are creating a room, please enter the room number (integer only):");
    }

    public void promptCreateEvent(){
        System.out.println("You are creating a event, please enter the following information line by line:");
        System.out.println("Enter event title:");
    }

    public void promptRoom(String roomList){
        System.out.println("Here are the available rooms");
        System.out.println(roomList);
        System.out.println("Enter room index from the above list (Starting from 1):");
    }

    public void promptSpeaker(String speakerList){
        System.out.println("Here are the available speakers");
        System.out.println(speakerList);
        System.out.println("Enter speaker index from the above list (Starting from 1):");
    }

    public void promptTime(){
        System.out.println("Enter time (yyyy-MM-dd HH:mm):");
    }

    public void promptEvent(){
        System.out.println("Enter event index from the above list (Starting from 1):");
    }

    public void printSchedule(String info){
        System.out.println("Here are the available events");
        System.out.println(info);
    }

    public void printVerification(){
        System.out.println("Successful modification!");
    }

    public void printInvalidInput() {
        System.out.println("Invalid input. Try again.");
    }

    public void printErrorMessage(){
        System.out.println("There is no entity of this type in the program yet");
        System.out.println("Program will now back to options");
    }
}
