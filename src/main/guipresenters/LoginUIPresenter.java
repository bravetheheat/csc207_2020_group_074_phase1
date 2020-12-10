package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.ProgramController;
import main.gui.*;
import main.gui_interface.ILandingUI;
import main.gui_interface.ILoginUI;
import main.gui_interface.INotificationUI;
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
    ILoginUI iLoginUI;
    ILandingUI iLandingUI;
    INotificationUI iRegisterMessageErrorUI;

    public LoginUIPresenter(ILoginUI loginUI, ProgramController programController) {
        this.iLoginUI = loginUI;
        this.programController = programController;
        this.authController = programController.getAuthController();
        this.iLoginUI.addLoginUIListener(this);
        this.iLoginUI.addBackButtonListener(this);
    }

    @Override
    public void onLoginButtonClicked() {
            String username = iLoginUI.getUserName();
            String password = iLoginUI.getPwd();
            if (authController.login(username, password)) {
                String userType = authController.getUserType();
                switch(userType){
                    case "Attendee":
//                        System.out.println("go to attendee");
                        programController.saveForNext();
                        new AttendeeMainUI(programController);
                        iLoginUI.dispose();
                        break;
                    case "Organizer":
//                        System.out.println("go to organizer");
                        programController.saveForNext();
                        new OrganizerMainUI(programController);
                        iLoginUI.dispose();
                        break;
                    case "Speaker":
//                        System.out.println("go to speaker");
                        programController.saveForNext();
                        new SpeakerMainUI(programController);
                        iLoginUI.dispose();
                        break;
                    default:
                        programController.saveForNext();
                        new RegisterMessageErrorPresenter(iRegisterMessageErrorUI,
                                programController);
                        iLoginUI.dispose();
                }
            }
    }

    @Override
    public void onBackButtonClicked() {
        this.iLandingUI = iLoginUI.goToLandingUI();
        new LandingUIPresenter(iLandingUI, programController);
//        iLoginUI.dispose();
    }
}
