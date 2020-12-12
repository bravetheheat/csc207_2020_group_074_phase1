package main.gui_interface;

import main.gui.ModifyEventUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ConfirmSelectSpeakerButtonListener;
import main.guilisteners.DeleteSpeakerButtonListener;

public interface IModifySpeakerUI extends IView {
    void addBackButtonListener(BackButtonListener listener);
    void addConfirmSelectSpeakerButtonListener(
            ConfirmSelectSpeakerButtonListener listener);
    ModifyEventUI goToModifyEventUI();
    void addDeleteSpeakerButtonListener(DeleteSpeakerButtonListener listener);
}
