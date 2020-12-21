package main.screencontrollers;

import main.controllers.ProgramController;
import main.presenters.SpeakerScreen;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The SpeakerScreenController decides what to display, receives
 * input from keyboard, and tells the ProgramController what screen to go next.
 *
 * @author Yile Xie
 * @version 3.0
 * @since 2020-11-12
 */
@Deprecated
public class SpeakerScreenController extends ScreenController {
    private final SpeakerScreen presenter;
    private final String loggedInSpeaker;
    private final ArrayList<String> validInput;

    /**
     * A ProgramController is needed to instantiate a SpeakerScreenController
     *
     * @param programController to be sent to the constructor of the super class
     */
    public SpeakerScreenController(ProgramController programController) {
        super(programController);
        this.presenter = new SpeakerScreen();
        this.loggedInSpeaker = this.programController.getAuthController().fetchLoggedInUser();
        this.validInput = new ArrayList<>(Arrays.asList("0", "1", "2"));
    }

    /**
     * Display the text on the Speaker Screen, initialize the collection of user input, and
     * go to the corresponding preceding screen
     */
    @Override
    public void start() {
        this.presenter.welcomeMessage();
        this.interact(this.loggedInSpeaker);
        this.end();
    }

    /**
     * Validate input from keyboard and tells ProgramController which screen to proceed to.
     */
    public void interact(String loggedInSpeaker) {
        this.presenter.prompt();
        String input = scanner.nextLine();

        while (!validInput.contains(input)) {
            this.presenter.errorMessage();
            this.presenter.prompt();
            input = scanner.nextLine();
        }

        switch (input) {
            case "0":
                this.programController.getAuthController().logout();
                return;
            case "1":
                ArrayList<String> talks =
                        this.IDtoString(this.programController.getEventController().getSpeakerEvents(loggedInSpeaker));
                this.presenter.talkList(talks);
                this.interact(loggedInSpeaker);
                break;
            case "2":
                this.programController.setNewScreenController(new SpeakerMessageScreenController(this.programController));
                break;

        }
    }

    /**
     * Return a list of strings for events, given a list of events String at which the speaker will be speaking.
     *
     * @param talkList list of String of the talks that the speaker will be giving
     * @return a list of the string representation of the talks
     */

    public ArrayList<String> IDtoString(ArrayList<String> talkList) {
        ArrayList<String> talksString = new ArrayList<>();
        for (String talk : talkList) {
            talksString.add(this.programController.getEventController().getSingleEventInfo(talk));
        }
        return talksString;
    }

}
