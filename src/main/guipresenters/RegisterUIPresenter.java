package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.ProgramController;
import main.gui.LandingUI;
import main.gui.RegisterMessageErrorUI;
import main.gui.RegisterMessageSuccessfulUI;
import main.gui.RegisterUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.RegisterUIListener;

/**
 * The presenter for <code>RegisterUI</code>
 *
 * @author Steven Yuan
 */
public class RegisterUIPresenter implements RegisterUIListener, BackButtonListener {

    RegisterUI registerUI;
    ProgramController programController;
    AuthController authController;
    LandingUI landingUI;
    RegisterMessageSuccessfulUI registerMessageSuccessfulUI;
    RegisterMessageErrorUI registerMessageErrorUI;

    public RegisterUIPresenter(RegisterUI registerUI, ProgramController programController) {
        this.registerUI = registerUI;
        this.programController = programController;
        this.authController = programController.getAuthController();
        registerUI.addRegisterUIListener(this);
        registerUI.addBackButtonListener(this);
    }
    @Override
    public void onConfirmButtonClicked() {
            String userType = registerUI.getUserType();
            String username = registerUI.getUserName();
            String password = registerUI.getPwd();
            if (!userType.equals("Attendee") && !userType.equals("Organizer")) {
                registerMessageErrorUI = new RegisterMessageErrorUI();
                new RegisterMessageErrorPresenter(registerMessageErrorUI, programController);
                registerUI.dispose();
            }
            boolean success = this.authController.registerUser(
                    username, password, userType);
            if (!success) {
                programController.saveForNext();
                registerMessageErrorUI = new RegisterMessageErrorUI();
                new RegisterMessageErrorPresenter(registerMessageErrorUI, programController);
                registerUI.dispose();
            }
            else {
                programController.saveForNext();
                registerMessageSuccessfulUI = new RegisterMessageSuccessfulUI();
                new RegisterMessageSuccessfulPresenter(
                        registerMessageSuccessfulUI, programController);
                registerUI.dispose();
            }
    }

    @Override
    public void onBackButtonClicked() {
        this.landingUI = new LandingUI();
        new LandingUIPresenter(landingUI, programController);
        registerUI.dispose();
    }
}
