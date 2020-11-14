package main.screencontrollers;

import main.controllers.ProgramController;
import main.presenters.SpeakerScreen;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The SpeakerScreenController decides what to display, receives
 * input from keyboard, and tells the ProgramController what screen to go next.
 *
 * @author Yile Xie
 * @version 2.0
 * @since 2020-11-12
 */

public class SpeakerScreenController extends ScreenController{
    private SpeakerScreen presenter;

    /**
     * A ProgramController is needed to instantiate a SpeakerScreenController
     * @param programController to be sent to the constructor of the super class
     */
    public SpeakerScreenController(ProgramController programController) {
        super(programController);
        this.presenter = new SpeakerScreen();
    }

    /**
     * Validate input from keyboard and tells ProgramController which screen to proceed to.
     */
    @Override
    public void start() {
        this.presenter.prompt();
        UUID loggedInSpeaker = this.programController.getAuthController().fetchLoggedInUser();
        ArrayList<String> validInput = new ArrayList<>();
        validInput.add("1");
        validInput.add("2");
        String input = scanner.nextLine();

        while (!validInput.contains(input)){
            this.presenter.errorMessage();
            this.presenter.prompt();
            input = scanner.nextLine();
        }

        switch (input) {
            case "1":
                ArrayList<String> talks =
                        this.IDtoString(this.programController.getEventController().getSpeakerEvents(loggedInSpeaker));
                this.presenter.talkList(talks);
                this.programController.setCurrentScreenController(this.previousScreenController);
            case "2":
//                this.programController.setCurrentScreenController(new MessageScreenController(this.programController));

        }
        this.end();
    }

    /**
     * Return a list of strings for events, given a list of events UUID at which the speaker will be speaking.
     * @param talkList list of UUID of the talks that the speaker will be giving
     * @return a list of the string representation of the talks
     */

    public ArrayList<String> IDtoString(ArrayList<UUID> talkList){
        ArrayList<String> talksString = new ArrayList<>();
        for (UUID talk : talkList){
            talksString.add(this.programController.getEventController().getSingleEventInfo(talk));
        }
        return talksString;
    }

}
