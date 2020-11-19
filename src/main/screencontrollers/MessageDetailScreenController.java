package main.screencontrollers;

import main.controllers.InboxController;
import main.controllers.ProgramController;
import main.presenters.MessageDetailScreen;

/**
 * The MessageDetailScreenController determines what the user would see when they choose to open a message
 *
 * @author Yile Xie
 */
public class MessageDetailScreenController extends ScreenController {
    String messageId;
    MessageDetailScreen presenter;

    /**
     * A ProgramController and a messageId (as a string) are required to instantiate a MessageDetailScreenController
     * @param programController of this program
     * @param messageId of the message this user selects
     */
    public MessageDetailScreenController(ProgramController programController, String messageId) {
        super(programController);
        this.messageId = messageId;
        this.presenter = new MessageDetailScreen();
    }

    /**
     * Display the details of the message and prompt the user to return
     */
    public void start() {
        this.printDetails(messageId);
        this.returnPrompt();
        this.end();
    }

    /**
     * Print out the details of the message with given ID, including the text, the sender, and the time this message
     * was sent
     */
    public void printDetails(String messageId) {
        InboxController inboxController = new InboxController(this.programController);
        String detail = inboxController.getMessageString(messageId);
        this.presenter.printDetails(detail);
    }

    /**
     * Prompt the user to enter 0 if they wish to return to their inbox screen
     */
    public void returnPrompt() {
        this.presenter.returnPrompt();
        String input = this.scanner.nextLine();
        while (!input.equals("0")) {
            this.presenter.errorMessage();
            this.presenter.returnPrompt();
            input = this.scanner.nextLine();
        }
        //this.programController.setPreviousScreenController(this);
        //this.programController.setCurrentScreenController(this.previousScreenController);
    }


}
