package main.guipresenters;

import main.controllers.ProgramController;
import main.gui_interface.IModifyEventUI;
import main.gui_interface.IModifySpeakerUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ConfirmSelectSpeakerButtonListener;
import main.guilisteners.DeleteSpeakerButtonListener;

public class ModifySpeakerUIPresenter
        implements BackButtonListener, ConfirmSelectSpeakerButtonListener, DeleteSpeakerButtonListener {

    ProgramController programController;
    private IModifySpeakerUI iModifySpeakerUI;
    private IModifyEventUI iModifyEventUI;

    public ModifySpeakerUIPresenter(IModifySpeakerUI modifySpeakerUI,
                                    ProgramController programController) {
        this.iModifySpeakerUI = modifySpeakerUI;
        this.programController = programController;
        iModifySpeakerUI.addBackButtonListener(this);
        iModifySpeakerUI.addConfirmSelectSpeakerButtonListener(this);
        iModifySpeakerUI.addDeleteSpeakerButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iModifyEventUI = iModifySpeakerUI.goToModifyEventUI();
        new ModifyEventUIPresenter(iModifyEventUI, programController);
    }

    @Override
    public void onConfirmSelectSpeakerButtonClicked() {

    }

    @Override
    public void onDeleteSpeakerButtonClicked() {

    }
}
