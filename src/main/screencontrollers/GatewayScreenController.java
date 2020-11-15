package main.screencontrollers;

import main.controllers.ProgramController;
import main.presenters.GatewayScreen;
import main.usecases.UsersManager;

import java.util.Arrays;
import java.util.List;

public class GatewayScreenController extends ScreenController {

    private final GatewayScreen presenter = new GatewayScreen();

    public GatewayScreenController(ProgramController programController) {
        super(programController);
    }

    public void start() {
        this.presenter.welcomeMessage();
        this.optionsPrompt();
    }

    private void optionsPrompt() {
        List<String> options = Arrays.asList("0", "1");
        this.presenter.optionsPrompt();
        String choice = this.scanner.nextLine();
        while (!options.contains(choice)) {
            this.presenter.invalidOption();
            choice = this.scanner.nextLine();
        }
        switch (choice) {
            case "0":
                this.programController.goToPreviousScreenController();
                this.end();
                return;
            case "1":
                this.saveUsers();
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
}
