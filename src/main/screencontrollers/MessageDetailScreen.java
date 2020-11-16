package main.screencontrollers;

import main.controllers.ProgramController;

public class MessageDetailScreen extends ScreenController {
    String messageId;

    public MessageDetailScreen(ProgramController programController, String messageId) {
        super(programController);
        this.messageId = messageId;
    }

    public void start() {


    }
}
