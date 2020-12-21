package main.screencontrollers;

import main.controllers.AuthController;
import main.controllers.ProgramController;
import main.presenters.LoginScreen;

/**
 * The LoginScreenController handles login functionality of the program
 *
 * @author David Zhao, Leyi Wang
 * @version 1.0
 */
@Deprecated
public class LoginScreenController extends ScreenController {
    private final LoginScreen presenter = new LoginScreen();
    private final AuthController authController;

    /**
     * Constructor for LoginScreenController
     *
     * @param programController instance of ProgramController
     */
    public LoginScreenController(ProgramController programController) {
        super(programController);
        this.authController = programController.getAuthController();
    }

    /**
     * Start and end the Login Screen.
     */
    @Override
    public void start() {
        this.presenter.printScreenName();
        this.optionsPrompt();
        this.end();
    }

    /**
     * Login or go to previous screen base on input command,
     * print the success or failure this command.
     */
    private void optionsPrompt() {
        //boolean validOptionChosen = false;
        this.presenter.optionsPrompt();
        String choice = this.scanner.nextLine();
        //while (!validOptionChosen) {
        switch(choice) {
            case"0":
                //validOptionChosen = true;
                this.goToPreviousScreenController();
                return;
            case"1":
                //validOptionChosen = true;
                this.login();
                return;
            default:
                this.presenter.invalidInput();
                this.optionsPrompt();
            }
        //}

    }

    /**
     * Login base on input command of username and password,
     * print success or failure of logging in
     */
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

    /**
     * Log out and print success or failure of logging out.
     */
    private void logout() {
        this.authController.logout();
        this.presenter.signout();
    }

}
