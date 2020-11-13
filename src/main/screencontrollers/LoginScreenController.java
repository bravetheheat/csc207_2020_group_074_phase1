package main.screencontrollers;

import main.controllers.AuthController;
import main.controllers.ProgramController;
import main.presenters.RegisterScreen;
import main.presenters.LoginScreen;

public class LoginScreenController extends ScreenController {
    private LoginScreen presenter = new LoginScreen();
    private AuthController authController;

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

    public void login(){
        String username;
        String password;
        String userType;

        this.presenter.promptUsername();
        username = this.scanner.nextLine();
        this.presenter.promptPassword();
        password = this.scanner.nextLine();

        if(!this.authController.isLoggedIn()){
            this.presenter.fail();
            this.logout();
        }

        boolean success = this.authController.login(username, password);
        if (!success) {
            this.presenter.error();
            this.login();
            return;
        }

        this.presenter.success();
    }

    public void logout(){
        this.authController.logout();
        this.presenter.signout();
    }

}
