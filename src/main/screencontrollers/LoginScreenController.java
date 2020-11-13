package main.screencontrollers;

import main.controllers.ProgramController;

public class LoginScreenController extends ScreenController {

    //store LoginScreen
    public LoginScreenController(ProgramController programController) {
        super(programController);
    }

    @Override
    public void start() {
        this.programController.setCurrentScreenController(new AnonymousScreenController(this.programController));
    }

}
