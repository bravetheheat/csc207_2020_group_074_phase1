package main.guipresenters;

import main.controllers.ProgramController;
import main.gui.RegisterMessageSuccessfulUI;
import main.gui.RegisterUI;
import main.guilisteners.BackButtonListener;

/**
 * The presenter for <code>RegisterMessageSuccessfulUI</code>
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class RegisterMessageSuccessfulPresenter implements BackButtonListener {

    ProgramController programController;
    private RegisterMessageSuccessfulUI registerMessageSuccessfulUI;
    private RegisterUI registerUI;

    public RegisterMessageSuccessfulPresenter(
            RegisterMessageSuccessfulUI registerMessageSuccessfulUI,
                                              ProgramController programController) {
        this.programController = programController;
        this.registerMessageSuccessfulUI = registerMessageSuccessfulUI;
        this.registerMessageSuccessfulUI.addBackButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        this.registerUI = new RegisterUI();
        new RegisterUIPresenter(registerUI, programController);
        registerMessageSuccessfulUI.dispose();
    }
}
