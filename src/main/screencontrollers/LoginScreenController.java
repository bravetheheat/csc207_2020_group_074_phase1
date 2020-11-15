package main.screencontrollers;

import main.controllers.AuthController;
import main.controllers.ProgramController;
import main.presenters.LoginScreen;

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
        this.login();
        this.end();
    }

    public void login() {
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
            this.login();
            return;
        }

        this.presenter.success();
        ScreenController nextScreenController = this.authController.getScreenController();
        this.programController.setNewScreenController(nextScreenController);

    }

    public void logout() {
        this.authController.logout();
        this.presenter.signout();
    }

}
