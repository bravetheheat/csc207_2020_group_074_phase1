package main.gui_interface;

import main.gui.EventsManagementUI;
import main.gui.SelectRoomUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ConfirmCreateEventButtonListener;
import main.guilisteners.SelectRoomButtonListener;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ICreateEventUI extends IView {
    void addBackButtonListener(BackButtonListener listener);
    void addSelectRoomButtonListener(SelectRoomButtonListener listener);
    void addConfirmButtonListener(ConfirmCreateEventButtonListener listener);
    void createNewEventSuccessful();
    void createNewEventError();
    EventsManagementUI goToEventsManagementUI();
    String getEventTitle();
    String getEventType();
    ArrayList<String> getEventConstraints();
    SelectRoomUI goToSelectRoomUI(ArrayList<Integer> listOfRoomInfo);
    String getEventDuration();
    String getEventCapacity();
    String getEventDate();
    void getValuesFromSelectRoomUI(String title, String type, int duration,
                                   int capacity, LocalDateTime date);
    void getRoomNumFromSelectRoomUI(int roomNum);
    int getRoomNum();
    String getEventTitleFromSelectRoomUI();

    String getEventTypeFromSelectRoomUI();

    int getEventDurationFromSelectRoomUI();

    int getEventCapacityFromSelectRoomUI();

    LocalDateTime getEventDateFromSelectRoomUI();
}
