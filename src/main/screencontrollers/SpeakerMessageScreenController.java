package main.screencontrollers;

import main.controllers.ProgramController;
import main.presenters.SpeakerMessageScreen;
import main.controllers.MessageController;
import main.entities.Event;
import java.util.*;


public class SpeakerMessageScreenController extends ScreenController{

    SpeakerMessageScreen speakerMessageScreen = new SpeakerMessageScreen();
    MessageController messageController;


    public SpeakerMessageScreenController(ProgramController programController){
        super(programController);
        this.messageController = programController.getMessageController();
    }

    public void start(){
        mainPart();
        end();
    }

    public void mainPart(){
        this.speakerMessageScreen.printScreenName();
        this.speakerMessageScreen.promt();
        String choice = this.scanner.nextLine();
        switch (choice) {
            case "0":
                break;
            case "1":
                reply();
                break;
            case "2":
                broadCast();
                break;
            default:
                this.speakerMessageScreen.invalidInput(choice);
                this.mainPart();
                return;
        }
        this.programController.setCurrentScreenController(new SpeakerScreenController(programController));
    }
    public void broadCast(){
        // how many events the user want to broadcast to
        List<String> events = messageController.eventsOfSpeakerInString(programController.getAuthController().fetchLoggedInUser());
        ArrayList<String> potentialAnswer= listBuilderWithInters(events.size());
        this.speakerMessageScreen.numOfEvents();
        String stringAnswer = this.scanner.nextLine();
        while(!potentialAnswer.contains(stringAnswer)){
            this.speakerMessageScreen.invalidInput(stringAnswer);
            stringAnswer = this.scanner.nextLine();
        }
        int integerAnswer = Integer.parseInt(stringAnswer);
        // now the user want to broadcast to integerAnswer number of events at once

        // gives options
        this.speakerMessageScreen.broadCastOptions(events);


        ArrayList<String> selectedEvents = new ArrayList<>();
        for(int i = 1; i <= integerAnswer; i++){
            stringAnswer = this.scanner.nextLine();
            while(!potentialAnswer.contains(stringAnswer)){
                this.speakerMessageScreen.invalidInput(stringAnswer);
                stringAnswer = this.scanner.nextLine();
            }
            selectedEvents.add(stringAnswer);
        }


        ArrayList<Event> allEvents = this.messageController.eventsOfSpeaker(programController.getAuthController().fetchLoggedInUser());
        ArrayList<UUID> finalTargetEvents = new ArrayList<UUID>();
        for(String identifier: selectedEvents){
            finalTargetEvents.add(allEvents.get(Integer.parseInt(identifier)-1).getId());
        }

        // now finalTargetEvents contains all events selected by user.

        this.speakerMessageScreen.whatMessage();
        String message = this.scanner.nextLine();

        // then broadcast
        this.messageController.broadCastForSpeakerMoreEvents(finalTargetEvents, programController.getAuthController().fetchLoggedInUser(), message);


    }

    public ArrayList<String> listBuilderWithInters(int num){
        int count = 1;
        ArrayList<String> ret= new ArrayList<>();
        while(count <= num){
            ret.add(Integer.toString(count));
            count++;
        }
        return ret;
    }

    public void reply(){

    }

}
