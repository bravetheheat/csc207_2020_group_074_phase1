package main.gui_interface;

import main.gui.EventsManagementUI;
import main.guilisteners.BackButtonListener;

public interface ISeeRoomsUI extends IView {
    EventsManagementUI goToEventsManagementUI();
    void addBackButtonListener(BackButtonListener listener);
}
