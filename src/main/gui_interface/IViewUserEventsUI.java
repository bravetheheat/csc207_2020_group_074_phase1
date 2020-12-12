package main.gui_interface;

import main.gui.EventSignUpUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.CancelEventButtonListener;

public interface IViewUserEventsUI extends IView {

    void addRegisterEventButtonListener(CancelEventButtonListener listener);
    void addBackButtonListener(BackButtonListener listener);
    EventSignUpUI goToEventSignUpUI();
    void cancelNewEventSuccessful();
    void cancelNewEventError();
    int getEventIndexFromList();
}
