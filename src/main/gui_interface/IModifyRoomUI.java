package main.gui_interface;

import main.gui.EventsManagementUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ConfirmModifyRoomButtonListener;

public interface IModifyRoomUI extends IView {
    void addBackButtonListener(BackButtonListener listener);
    void addConfirmButtonListener(ConfirmModifyRoomButtonListener listener);
    void modifyRoomSuccessful();
    void modifyRoomError();
    EventsManagementUI goToEventsManagementUI();
    String getRoomNum();
    String getConstraint();
}
