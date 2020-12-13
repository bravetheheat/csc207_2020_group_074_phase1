package main.gui_interface;

import main.gui.DeleteAnEventUI;
import main.gui.EventsManagementUI;
import main.gui.ModifySpeakerUI;
import main.gui.SelectRoomUI;
import main.guilisteners.*;

import java.util.ArrayList;

public interface IModifyEventUI extends IView {
    void modifyEventSuccessful();
    void modifyEventError();
    EventsManagementUI goToEventsManagementUI();
    void addBackButtonListener(BackButtonListener listener);
    void addGetEventsButtonListener(GetEventsButtonListener listener);
    void addModifySpeakerButtonListener(ModifySpeakerButtonListener listener);
    void addConfirmModifyEventButtonListener(ConfirmModifyEventButtonListener listener);
    DeleteAnEventUI goToDeleteAnEventUI(ArrayList<String> listOfEventsInfo);
    String getEventTime();
    String getEventCapacity();
//    String getRoomNum();
    void addSelectRoomButtonListener(SelectRoomButtonListener listener);
    SelectRoomUI goToSelectRoomUI(ArrayList<Integer> listOfRoomInfo,
                                  ArrayList<String> constraints);
    String getRoomConstraints();
    int getEventIndex();
    ModifySpeakerUI goToModifySpeakerUI(ArrayList<String> listOfSpeakerInfo,
                                        ArrayList<String> listOfEventSpeakers);
    void storeEventIndex(int eventIndex);
    void storeRoomNum(int roomNum);
    int getRoomNum();
}
