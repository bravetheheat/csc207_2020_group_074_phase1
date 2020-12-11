package main.screencontrollers;

import main.controllers.AuthController;
import main.controllers.ProgramController;
import main.presenters.RegisterScreen;

/**
 * RegisterScreenController handles the user registration interface
 *
 * @author David Zhao
 */
public class RegisterScreenController extends ScreenController {

    private final RegisterScreen presenter = new RegisterScreen();
    private final AuthController authController;

    public RegisterScreenController(ProgramController programController) {
        super(programController);
        this.authController = programController.getAuthController();
    }

    /**
     * Call the presenter to print the screen name and text messages that the users would see during their
     * registration
     */
    public void start() {
        this.presenter.printScreenName();
        this.registration();
        this.end();

    }

    private void registration() {
        String username;
        String password;
        String userType;

        this.presenter.promptUsername();
        username = this.scanner.nextLine();
        this.presenter.promptPassword();
        password = this.scanner.nextLine();
        userType = this.promptUserType();

        boolean success = this.authController.registerUser(username, password, userType);
        if (!success) {
            this.presenter.error();
            this.registration();
            return;
        }

        this.presenter.success();
        this.goToPreviousScreenController();

    }

    private String promptUserType() {
        String userType;
        this.presenter.promptUserType();
        String userTypeChoice = this.scanner.nextLine();
        switch (userTypeChoice) {
            case "1":
                userType = "Attendee";
                break;
            case "2":
                userType = "Organizer";
                break;
            case "3":
                userType = "Admin";
                break;
            case "4":
                userType = "Speaker";
                break;
            default:
                this.presenter.invalidInput();
                return this.promptUserType();

        }
        return userType;

    }

}
