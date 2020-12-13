package main.gui_interface;

import main.gui.ModifyEventUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.DeleteEventButtonListener;
import main.guilisteners.SelectEventButtonListener;

public interface IDeleteAnEventUI extends IView {
    int getEventIndexFromList();
    void addDeleteEventButtonListener(DeleteEventButtonListener listener);
    void deleteEventSuccessful();
    void addBackButtonListener(BackButtonListener listener);
    void deleteEventError();
    ModifyEventUI goToModifyEventUI();
    void addSelectEventButtonListener(SelectEventButtonListener listener);
    int getEventIndex();
}
