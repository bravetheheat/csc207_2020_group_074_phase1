package main.guipresenters;

import main.controllers.ProgramController;
import main.gui.RegisterMessageErrorUI;
import main.gui.RegisterUI;
import main.guilisteners.BackButtonListener;

/**
 * The presenter for <code>RegisterMessageErrorUI</code>
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class RegisterMessageErrorPresenter implements BackButtonListener {

    private ProgramController programController;
    private RegisterMessageErrorUI registerMessageErrorUI;
    private RegisterUI registerUI;

    public RegisterMessageErrorPresenter(RegisterMessageErrorUI registerMessageErrorUI,
                                         ProgramController programController) {
        this.programController = programController;
        this.registerMessageErrorUI = registerMessageErrorUI;
        registerMessageErrorUI.addBackButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        this.registerUI = new RegisterUI();
        new RegisterUIPresenter(registerUI, programController);
        registerMessageErrorUI.dispose();
    }
}
