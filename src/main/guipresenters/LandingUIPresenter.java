package main.guipresenters;

import main.controllers.ProgramController;
import main.gui.LandingUI;
import main.gui.LoginUI;
import main.gui.RegisterUI;
import main.guilisteners.LoginButtonListener;
import main.guilisteners.RegisterButtonListener;

/**
 * The presenter for <code>LandingUI</code>
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class LandingUIPresenter implements LoginButtonListener, RegisterButtonListener {

    private LandingUI landingUI;
    private ProgramController programController;
    private LoginUI loginUI;
    private RegisterUI registerUI;

    public LandingUIPresenter(LandingUI landingUI, ProgramController programController) {
        this.landingUI = landingUI;
        this.programController = programController;
        programController.startUI();
        landingUI.addLoginButtonListener(this);
        landingUI.addRegisterButtonLister(this);
    }

    @Override
    public void onLoginButtonClicked() {
        programController.saveForNext();
        loginUI = new LoginUI();
        new LoginUIPresenter(loginUI, programController);
        landingUI.dispose();
    }

    @Override
    public void onRegisterButtonClicked() {
        programController.saveForNext();
        registerUI = new RegisterUI();
        new RegisterUIPresenter(registerUI, programController);
        landingUI.dispose();
    }
}
