package main.screencontrollers;

import main.controllers.ProgramController;
import main.presenters.GatewayScreen;
import main.usecases.EventsManager;
import main.usecases.MessageManager;
import main.usecases.RoomManager;
import main.usecases.UsersManager;

import java.util.Arrays;
import java.util.List;

/**
 * GatewayScreenController handles the saving and loading of data from the default gateway.
 *
 * @author David Zhao
 */
public class GatewayScreenController extends ScreenController {

    private final GatewayScreen presenter = new GatewayScreen();

    public GatewayScreenController(ProgramController programController) {
        super(programController);
    }

    public void start() {
        this.presenter.welcomeMessage();
        this.optionsPrompt();
        this.end();
    }

    private void optionsPrompt() {
        List<String> options = Arrays.asList("0", "1", "2", "3", "4");
        this.presenter.optionsPrompt();
        String choice = this.scanner.nextLine();
        while (!options.contains(choice)) {
            this.presenter.invalidOption();
            choice = this.scanner.nextLine();
        }
        switch (choice) {
            case "0":
                this.goToPreviousScreenController();

                return;
            case "1":
                this.saveUsers();
                break;
            case "2":
                this.saveRooms();
                break;

            case "3":
                this.saveEvents();
                break;
            case "4":
                this.saveMessages();
                break;
        }
        return;

    }

    private void saveUsers() {
        this.presenter.saveUsers();
        UsersManager usersManager = this.programController.getUsersManager();
        usersManager.saveUsersToGateway(this.programController.getGateway());
        this.presenter.success();
        this.optionsPrompt();
    }

    private void saveRooms() {
        this.presenter.saveRooms();
        RoomManager roomManager = this.programController.getRoomManager();
        roomManager.saveRoomsFromGateway(this.programController.getGateway());
        this.presenter.success();
        this.optionsPrompt();
    }

    private void saveEvents() {
        this.presenter.saveEvents();
        EventsManager eventsManager = this.programController.getEventsManager();
        eventsManager.saveEventsToGateway(this.programController.getGateway());
        this.presenter.success();
        this.optionsPrompt();
    }

    private void saveMessages() {
        this.presenter.saveMessages();
        MessageManager messageManager = this.programController.getMessageManager();
        messageManager.saveMessagesToGateway(this.programController.getGateway());
        this.presenter.success();
        this.optionsPrompt();
    }
}
