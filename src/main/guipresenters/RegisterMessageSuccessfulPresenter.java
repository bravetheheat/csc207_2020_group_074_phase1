package main.guipresenters;

import main.controllers.ProgramController;
import main.gui.RegisterUI;
import main.gui_interface.IRegisterMessageSuccessfulUI;
import main.guilisteners.BackButtonListener;

/**
 * The presenter for <code>RegisterMessageSuccessfulUI</code>
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class RegisterMessageSuccessfulPresenter implements BackButtonListener {

    ProgramController programController;
    private IRegisterMessageSuccessfulUI iRegisterMessageSuccessfulUI;
    private RegisterUI iRegisterUI;

    public RegisterMessageSuccessfulPresenter(
            IRegisterMessageSuccessfulUI registerMessageSuccessfulUI,
                                              ProgramController programController) {
        this.programController = programController;
        this.iRegisterMessageSuccessfulUI = registerMessageSuccessfulUI;
        this.iRegisterMessageSuccessfulUI.addBackButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
//        this.registerUI = new RegisterUI();
//        new RegisterUIPresenter(registerUI, programController);
//        iRegisterMessageSuccessfulUI.dispose();
        iRegisterUI = iRegisterMessageSuccessfulUI.goToRegister();
        new RegisterUIPresenter(iRegisterUI, programController);
    }
}
