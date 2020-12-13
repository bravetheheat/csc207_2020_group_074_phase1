package main.gui_interface;

import main.gui.CreateEventUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ConfirmSelectRoomButtonListener;

import java.time.LocalDateTime;

public interface ISelectRoomUI {
    void addBackButtonListener(BackButtonListener listener);

    CreateEventUI goToCreateEventUI();

    void addSelectButtonListener(ConfirmSelectRoomButtonListener listener);

    void selectRoomSuccessful();

    void selectRoomError();

    int getRoomIndex();

    int getRoomNum();

    void storeValuesFromCreateEventUI(String title, String type, int duration,
                                      int capacity, LocalDateTime date);

    String getEventTitle();

    String getEventType();

    int getEventDuration();

    int getEventCapacity();

    LocalDateTime getEventDate();

}