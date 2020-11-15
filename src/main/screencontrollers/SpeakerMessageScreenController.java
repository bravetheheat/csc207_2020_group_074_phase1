package main.screencontrollers;

import main.controllers.ProgramController;
import main.presenters.SpeakerMessageScreen;
import main.controllers.MessageController;
import main.entities.Event;
import java.util.*;


public class SpeakerMessageScreenController extends ScreenController{

    SpeakerMessageScreen speakerMessageScreen = new SpeakerMessageScreen();
    MessageController messageController;
    UUID speaker;


    public SpeakerMessageScreenController(ProgramController programController){
        super(programController);
        this.messageController = programController.getMessageController();
        speaker = programController.getAuthController().fetchLoggedInUser();
    }

    public void start(){
        this.speakerMessageScreen.printScreenName();
        mainPart();
        end();
    }

    public void mainPart(){
        this.speakerMessageScreen.promt();
        String choice = this.scanner.nextLine();
        switch (choice) {
            case "0":
                this.programController.setCurrentScreenController(new SpeakerScreenController(programController));
                break;
            case "1":
                reply();
                this.programController.setCurrentScreenController(new SpeakerScreenController(programController));
                break;
            case "2":
                broadCast();
                this.programController.setCurrentScreenController(new SpeakerScreenController(programController));
                break;
            case "3":
                this.programController.setCurrentScreenController(new InboxScreenController(programController));
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
        List<String> events = messageController.eventsOfSpeakerInString(speaker);
        ArrayList<String> potentialAnswer= listBuilderWithIntegers(events.size());
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


        ArrayList<Event> allEvents = this.messageController.eventsOfSpeaker(speaker);
        ArrayList<UUID> finalTargetEvents = new ArrayList<>();
        for(String identifier: selectedEvents){
            finalTargetEvents.add(allEvents.get(Integer.parseInt(identifier)-1).getId());
        }

        // now finalTargetEvents contains all events selected by user.

        this.speakerMessageScreen.whatMessage();
        String message = this.scanner.nextLine();

        // then broadcast
        this.messageController.broadCastForSpeakerMoreEvents(finalTargetEvents, speaker, message);

        speakerMessageScreen.congratulations();


    }

    public ArrayList<String> listBuilderWithIntegers(int num){
        int count = 1;
        ArrayList<String> ret= new ArrayList<>();
        while(count <= num){
            ret.add(Integer.toString(count));
            count++;
        }
        return ret;
    }

    public void reply(){
        ArrayList<UUID> potentialReceivers= messageController.replyOptionsForSpeaker(speaker);
        ArrayList<String> options = listBuilderWithIntegers(potentialReceivers.size());
        speakerMessageScreen.replyOptions(messageController.replyOptionsForSpeakerInString(speaker));
        String answerInString = scanner.nextLine();
        while(! options.contains(answerInString)){
            speakerMessageScreen.invalidInput(answerInString);
            answerInString = scanner.nextLine();
        }
        // now answer is valid

        int answerInInteger = Integer.parseInt(answerInString);
        UUID receiver = potentialReceivers.get(answerInInteger - 1);

        speakerMessageScreen.whatMessage();
        String messageContext = scanner.nextLine();
        messageController.sendMessage(speaker, receiver, messageContext);
        speakerMessageScreen.congratulations();
    }

}
