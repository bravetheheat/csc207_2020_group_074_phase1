package main.gui_interface;

import main.gui.EventsManagementUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ConfirmCreateRoomButtonListener;

public interface ICreateRoomUI extends IView {
    void addBackButtonListener(BackButtonListener listener);
    void addConfirmButtonListener(ConfirmCreateRoomButtonListener listener);
    String getRoomNum();
    String getCapacity();
    EventsManagementUI goToEventsManagementUI();
    void createNewRoomSuccessful();
    void createNewRoomError();
}
