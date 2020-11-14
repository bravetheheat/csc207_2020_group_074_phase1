package main.screencontrollers;

import main.controllers.ProgramController;

import java.util.UUID;

public class MessageDetailScreen extends ScreenController {
    UUID messageId;

    public MessageDetailScreen(ProgramController programController, UUID messageId) {
        super(programController);
        this.messageId = messageId;
    }

    public void start() {}
}
