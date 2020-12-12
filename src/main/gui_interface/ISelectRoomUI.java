package main.gui_interface;

import main.gui.CreateEventUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ConfirmSelectRoomButtonListener;

public interface ISelectRoomUI {
    void addBackButtonListener(BackButtonListener listener);
    CreateEventUI goToCreateEventUI();
    void addSelectButtonListener(ConfirmSelectRoomButtonListener listener);
    void selectRoomSuccessful();
    void selectRoomError();
    int getRoomIndex();
    int getRoomNum();
}
