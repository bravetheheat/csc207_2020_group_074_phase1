package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.ProgramController;
import main.gui.*;
import main.guilisteners.BackButtonListener;
import main.guilisteners.LoginUIListener;

/**
 * The presenter for <code>LoginUI</code>
 *
 * @author Steven Yuan
 */
public class LoginUIPresenter implements LoginUIListener, BackButtonListener {

    ProgramController programController;
    AuthController authController;
    LoginUI loginUI;
    LandingUI landingUI;
    RegisterMessageErrorUI registerMessageErrorUI;

    public LoginUIPresenter(LoginUI loginUI, ProgramController programController) {
        this.loginUI = loginUI;
        this.programController = programController;
        this.authController = programController.getAuthController();
        loginUI.addLoginUIListener(this);
        loginUI.addBackButtonListener(this);
    }

    @Override
    public void onLoginButtonClicked() {
            String username = loginUI.getUserName();
            String password = loginUI.getPwd();
            if (authController.login(username, password)) {
                String userType = authController.getUserType();
                switch(userType){
                    case "Attendee":
                        System.out.println("go to attendee");
                        programController.saveForNext();
                        new AttendeeMainUI(programController);
                        loginUI.dispose();
                        break;
                    case "Organizer":
                        System.out.println("go to organizer");
                        programController.saveForNext();
                        new OrganizerMainUI(programController);
                        loginUI.dispose();
                        break;
                    case "Speaker":
                        System.out.println("go to speaker");
                        programController.saveForNext();
                        new SpeakerMainUI(programController);
                        loginUI.dispose();
                        break;
                    default:
                        programController.saveForNext();
                        new RegisterMessageErrorPresenter(registerMessageErrorUI,
                                programController);
                        loginUI.dispose();
                }
            }
    }

    @Override
    public void onBackButtonClicked() {
        this.landingUI = new LandingUI();
        new LandingUIPresenter(landingUI, programController);
        loginUI.dispose();
    }
}
