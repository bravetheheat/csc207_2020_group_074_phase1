package main.screencontrollers;

import main.controllers.ProgramController;
import main.presenters.AnonymousScreen;

public class AnonymousScreenController extends ScreenController {
    AnonymousScreen presenter = new AnonymousScreen();

    public AnonymousScreenController(ProgramController programController) {
        super(programController);
    }

    public void start() {
        this.presenter.printWelcomeMessage();
        this.mainOptions();
        this.end();
    }
    private void mainOptions() {
        this.presenter.printOptions();
        String choice = this.scanner.nextLine();

        ScreenController nextScreenController;
        switch(choice) {
            case "1":
                nextScreenController = new LoginScreenController(this.programController);
                break;
            case "2":
                nextScreenController = new RegisterScreenController(this.programController);
                break;
            default:
                this.presenter.printInvalidInputErrorMessage();
                this.mainOptions();
                return;
        }
        this.programController.setCurrentScreenController(nextScreenController);
    }
}
