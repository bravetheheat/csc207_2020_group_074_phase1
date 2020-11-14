package main.screencontrollers;

import main.controllers.AuthController;
import main.controllers.InboxController;
import main.controllers.ProgramController;
import main.entities.Inbox;
import main.entities.Message;
import main.presenters.InboxScreen;
import main.usecases.InboxManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InboxScreenController extends ScreenController {

    InboxScreen presenter = new InboxScreen();
    InboxController inboxController;
    Map<UUID, String> messages;

    public InboxScreenController(ProgramController programController) {
        super(programController);
        this.inboxController = new InboxController(programController.getMessageManager(), programController.getInboxManager(), programController.getUsersManager());
    }

    public void start() {
        this.presenter.welcomeMessage();
        this.optionsPrompt();
        this.end();

    }

    private void optionsPrompt() {
        this.presenter.optionsPrompt();
        String choice = this.scanner.nextLine();
        switch(choice) {
            case "0":
                this.programController.setCurrentScreenController(this.programController.getPreviousScreenController());
                return;
            case "1":
                this.listMessages();
                break;
            default:
                this.presenter.invalidOption();
                this.optionsPrompt();
                break;
        }
    }

    private void fetchMessages() {
        AuthController currentAuthController = this.programController.getAuthController();
        UUID currentUserId = currentAuthController.fetchLoggedInUser();
        this.messages = this.inboxController.getMessagesOfUser(currentUserId);
    }

    private void listMessages() {
        this.fetchMessages();
        this.presenter.listMessages((List) this.messages.values()); // TODO: Don't use casting
        this.messageDetailPrompt();

    }

    private void messageDetailPrompt() {
        this.presenter.selectMessagePrompt();
        String choice = this.scanner.nextLine();
        if (choice.equals("0")) {
            this.optionsPrompt();
            return;
        }

        try{
            int messageNum = Integer.parseInt(choice);
            if (messageNum > this.messages.size()) {
                this.presenter.invalidOption();
                this.messageDetailPrompt();
                return;
            }
        }
        catch (NumberFormatException e) {
            this.presenter.invalidOption();
            this.messageDetailPrompt();
            return;
        }

    }

    private void openMessageDetailScreen(UUID messageId) {
        ScreenController messageDetailScreen = new MessageDetailScreen(this.programController, messageId);
        this.programController.setPreviousScreenController(this);
        this.programController.setCurrentScreenController(messageDetailScreen);
        this.programController.nextScreenController();
    }
}
