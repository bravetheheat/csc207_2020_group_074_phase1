package main.gui_interface;

import main.gui.EventSignUpUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.RegisterEventButtonListener;

public interface IDisplayAllEventsUI extends IView {

    void addRegisterEventButtonListener(RegisterEventButtonListener listener);
    void addBackButtonListener(BackButtonListener listener);
    EventSignUpUI goToEventSignUpUI();
    int getEventIndexFromList();
    void registerNewEventSuccessful();
    void registerNewEventError();
}
