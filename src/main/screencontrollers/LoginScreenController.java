package main.screencontrollers;

import main.controllers.AuthController;
import main.controllers.ProgramController;
import main.presenters.LoginScreen;

/**
 * The LoginScreenController handles login functionality of the program
 *
 * @author David Zhao
 */
public class LoginScreenController extends ScreenController {
    private final LoginScreen presenter = new LoginScreen();
    private final AuthController authController;

    public LoginScreenController(ProgramController programController) {
        super(programController);
        this.authController = programController.getAuthController();
    }

    @Override
    public void start() {
        this.presenter.printScreenName();
        this.optionsPrompt();
        this.end();
    }


    private void optionsPrompt() {
        boolean validOptionChosen = false;
        this.presenter.optionsPrompt();
        String choice = this.scanner.nextLine();
        while (!validOptionChosen) {
            switch(choice) {
                case"0":
                    validOptionChosen = true;
                    this.goToPreviousScreenController();

                    return;
                case"1":
                    validOptionChosen = true;
                    this.login();
                    return;
            }
        }

    }

    private void login() {
        String username;
        String password;

        this.presenter.promptUsername();
        username = scanner.nextLine();
        this.presenter.promptPassword();
        password = scanner.nextLine();

        if (this.authController.isLoggedIn()) {
            this.presenter.fail();
            this.logout();
            return;
        }

        boolean success = this.authController.login(username, password);
        if (!success) {
            this.presenter.error();
            this.optionsPrompt();
            return;
        }

        this.presenter.success();
        ScreenController nextScreenController = this.authController.getScreenController();
        this.programController.setNewScreenController(nextScreenController);

    }

    private void logout() {
        this.authController.logout();
        this.presenter.signout();
    }

}
