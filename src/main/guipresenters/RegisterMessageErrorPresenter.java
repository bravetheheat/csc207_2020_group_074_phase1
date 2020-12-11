package main.guipresenters;

import main.controllers.ProgramController;
import main.gui_interface.INotificationUI;
import main.gui_interface.IRegisterUI;
import main.guilisteners.BackButtonListener;

/**
 * The presenter for <code>RegisterMessageErrorUI</code>
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class RegisterMessageErrorPresenter implements BackButtonListener {

    private ProgramController programController;
    private INotificationUI iRegisterMessageErrorUI;
    private IRegisterUI iRegisterUI;

    public RegisterMessageErrorPresenter(INotificationUI registerMessageErrorUI,
                                         ProgramController programController) {
        this.programController = programController;
        this.iRegisterMessageErrorUI = registerMessageErrorUI;
        this.iRegisterMessageErrorUI.addBackButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iRegisterUI = iRegisterMessageErrorUI.goToRegister();
        new RegisterUIPresenter(iRegisterUI, programController);
//        iRegisterMessageErrorUI.dispose();
    }
}
