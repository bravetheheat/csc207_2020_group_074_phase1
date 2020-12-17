package main.guipresenters;

import main.controllers.ProgramController;
import main.gui_interface.ILandingUI;
import main.gui_interface.ILoginUI;
import main.gui_interface.IRegisterUI;
import main.guilisteners.LoginButtonListener;
import main.guilisteners.RegisterButtonListener;

/**
 * The presenter for <code>LandingUI</code>
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class LandingUIPresenter implements LoginButtonListener, RegisterButtonListener {

    private ILandingUI iLandingUI;
    private ProgramController programController;
    private ILoginUI iLoginUI;
    private IRegisterUI iRegisterUI;

    public LandingUIPresenter(ILandingUI landingUI, ProgramController programController) {
        this.iLandingUI = landingUI;
        this.programController = programController;
        programController.startUI();
        iLandingUI.addLoginButtonListener(this);
        iLandingUI.addRegisterButtonLister(this);
    }

    @Override
    public void onLoginButtonClicked() {
        iLoginUI = iLandingUI.goToLoginUI();
        new LoginUIPresenter(iLoginUI, programController);
    }

    @Override
    public void onRegisterButtonClicked() {
        iRegisterUI = iLandingUI.goToRegisterUI();
        new RegisterUIPresenter(iRegisterUI, programController);
    }
}
