package main.screencontrollers;

import main.controllers.ProgramController;
import main.presenters.SpeakerMessageScreen;
import main.controllers.MessageController;
import main.entities.Event;
import java.util.*;


/**
 * SpeakerMessageScreenController is the controller of the SpeakerMessageScreen which in charge of
 * handling all message functions for speakers.
 * @author Ruoming Ren
 * @since 2020-11-13
 */
public class SpeakerMessageScreenController extends ScreenController{

    SpeakerMessageScreen speakerMessageScreen = new SpeakerMessageScreen();
    MessageController messageController;
    String speaker;


    /**
     * The constructor that construct a Speaker Message screen
     * @param programController the main controller of the program.
     */
    public SpeakerMessageScreenController(ProgramController programController){
        super(programController);
        this.messageController = programController.getMessageController();
        speaker = programController.getAuthController().fetchLoggedInUser();
    }

    /**
     * The method which will start running the screen
     */
    public void start(){
        this.speakerMessageScreen.printScreenName();
        mainPart();
        end();
    }

    /**
     * the main mart of the screen controller
     */
    public void mainPart(){
        this.speakerMessageScreen.promt();
        String choice = this.scanner.nextLine();
        switch (choice) {
            case "0":
                this.programController.goToPreviousScreenController();
                break;
            case "1":
                this.reply();
                this.mainPart();
                break;
            case "2":
                this.broadCast();
                this.mainPart();
                break;
            case "3":
                this.programController.setNewScreenController(new InboxScreenController(programController));
                break;
            default:
                this.speakerMessageScreen.invalidInput(choice);
                this.mainPart();
                return;
        }

        speakerMessageScreen.willBeReturned();
        this.programController.setNewScreenController(new SpeakerScreenController(programController));
    }

    /**
     * this method will make the speaker message screen ask the speaker for the context he/she
     * wants to broadcast, and the receivers he/she wants to broadcast to.
     */
    public void broadCast(){
        // how many events the user want to broadcast to
        List<String> events = messageController.eventsOfSpeakerInString(speaker);
        ArrayList<String> potentialAnswer= listBuilderWithIntegers(events.size());
        if(potentialAnswer.size() != 0){
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
            ArrayList<String> finalTargetEvents = new ArrayList<>();
            for(String identifier: selectedEvents){
                finalTargetEvents.add(allEvents.get(Integer.parseInt(identifier)-1).getId());
            }

            // now finalTargetEvents contains all events selected by user.

            this.speakerMessageScreen.whatMessage();
            String message = this.scanner.nextLine();

            // then broadcast
            this.messageController.broadCastForSpeakerMoreEvents(finalTargetEvents, speaker, message);

            speakerMessageScreen.congratulations();
        }else{
            speakerMessageScreen.noEvent();
        }


    }

    private ArrayList<String> listBuilderWithIntegers(int num){
        int count = 1;
        ArrayList<String> ret= new ArrayList<>();
        while(count <= num){
            ret.add(Integer.toString(count));
            count++;
        }
        return ret;
    }

    /**
     * This method will make the speaker message screen ask the speaker for the attendees he/she
     * wants to reply to and the context he/she wants to message
     */
    public void reply(){
        ArrayList<String> potentialReceivers= messageController.replyOptionsForSpeaker(speaker);
        ArrayList<String> options = listBuilderWithIntegers(potentialReceivers.size());
        speakerMessageScreen.replyOptions(messageController.replyOptionsForSpeakerInString(speaker));
        if(messageController.replyOptionsForSpeakerInString(speaker).size() != 0) {
            String answerInString = scanner.nextLine();
            while(!options.contains(answerInString)){
                speakerMessageScreen.invalidInput(answerInString);
                answerInString = scanner.nextLine();
            }

            // now answer is valid

            int answerInInteger = Integer.parseInt(answerInString);
            String receiver = potentialReceivers.get(answerInInteger - 1);

            speakerMessageScreen.whatMessage();
            String messageContext = scanner.nextLine();
            messageController.sendMessage(speaker, receiver, messageContext);
            speakerMessageScreen.congratulations();
        }

    }

}
