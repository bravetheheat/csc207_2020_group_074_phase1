package main.gui_interface;


import main.gui.*;
import main.guilisteners.*;

public interface IEventsManagementUI extends IView {
    void addBackButtonListener(BackButtonListener listener);
    void addCreateRoomButtonListener(CreateRoomButtonListener listener);
    void addModifyRoomButtonListener(ModifyRoomButtonListener listener);
    void addCreateEventButtonListener(CreateEventButtonListener listener);
    void addModifyEventButtonListener(ModifyEventButtonListener listener);
    OrganizerMainUI goToOrganizerMainUI();
    CreateRoomUI goToCreateRoomUI();
    ModifyRoomUI goToModifyRoomUI();
    CreateEventUI goToCreateEventUI();
    ModifyEventUI goToModifyEventUI();
}
