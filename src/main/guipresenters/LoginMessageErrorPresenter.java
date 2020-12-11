package main.guipresenters;

import main.controllers.ProgramController;
import main.gui_interface.ILoginMessageErrorUI;
import main.gui_interface.ILoginUI;
import main.guilisteners.BackButtonListener;

/**
 * The presenter for <code>LoginMessageErrorUI</code>
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class LoginMessageErrorPresenter implements BackButtonListener {

    private ProgramController programController;
    private ILoginMessageErrorUI iLoginMessageErrorUI;
    private ILoginUI iLoginUI;

    public LoginMessageErrorPresenter(ILoginMessageErrorUI loginMessageErrorUI,
                                         ProgramController programController) {
        this.programController = programController;
        this.iLoginMessageErrorUI = loginMessageErrorUI;
        this.iLoginMessageErrorUI.addBackButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iLoginUI = iLoginMessageErrorUI.goToLogin();
        new LoginUIPresenter(iLoginUI, programController);
    }
}
