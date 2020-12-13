package main.gui_interface;


import main.gui.*;
import main.guilisteners.*;

import java.util.ArrayList;

public interface IEventsManagementUI extends IView {
    void addBackButtonListener(BackButtonListener listener);
    void addCreateRoomButtonListener(CreateRoomButtonListener listener);
    void addModifyRoomButtonListener(ModifyRoomButtonListener listener);
    void addCreateEventButtonListener(CreateEventButtonListener listener);
    void addModifyEventButtonListener(ModifyEventButtonListener listener);
    void addSeeRoomsButtonListener(SeeRoomsButtonListener listener);
    OrganizerMainUI goToOrganizerMainUI();
    CreateRoomUI goToCreateRoomUI();
    ModifyRoomUI goToModifyRoomUI();
    CreateEventUI goToCreateEventUI();
    ModifyEventUI goToModifyEventUI();
    SeeRoomsUI goToSeeRoomsUI(ArrayList<Integer> listOfRoomsInfo);
    void addSeeScheduleButtonListener(SeeScheduleButtonListener listener);
    SeeScheduleUI goToSeeScheduleUI(ArrayList<String> listOfEventInfo);
}
