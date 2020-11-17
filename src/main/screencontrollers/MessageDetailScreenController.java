package main.screencontrollers;

import main.controllers.InboxController;
import main.controllers.ProgramController;
import main.presenters.MessageDetailScreen;

public class MessageDetailScreenController extends ScreenController {
    String messageId;
    MessageDetailScreen presenter;

    public MessageDetailScreenController(ProgramController programController, String messageId) {
        super(programController);
        this.messageId = messageId;
        this.presenter = new MessageDetailScreen();
    }

    public void start() {
        this.printDetails(messageId);
        this.returnPrompt();
        this.end();
    }

    public void printDetails(String messageId) {
        InboxController inboxController = new InboxController(this.programController);
        String detail = inboxController.getMessageString(messageId);
        this.presenter.printDetails(detail);
    }

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
