package main.guipresenters;

import main.controllers.OrganizerController;
import main.controllers.ProgramController;
import main.gui_interface.IEventsManagementUI;
import main.gui_interface.IModifyRoomUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ConfirmModifyRoomButtonListener;

import java.util.ArrayList;
import java.util.Arrays;

public class ModifyRoomUIPresenter
        implements BackButtonListener, ConfirmModifyRoomButtonListener {

    ProgramController programController;
    OrganizerController organizerController;
    IModifyRoomUI iModifyRoomUI;
    IEventsManagementUI iEventsManagementUI;

    public ModifyRoomUIPresenter(IModifyRoomUI modifyRoomUI,
                                 ProgramController programController) {
        this.programController = programController;
        this.organizerController = new OrganizerController(programController);
        this.iModifyRoomUI = modifyRoomUI;
        iModifyRoomUI.addBackButtonListener(this);
        iModifyRoomUI.addConfirmButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iEventsManagementUI = iModifyRoomUI.goToEventsManagementUI();
        new EventsManagementUIPresenter(iEventsManagementUI, programController);
    }

    @Override
    public void onConfirmModifyRoomButtonClicked() {
        try {
            String roomInput = iModifyRoomUI.getRoomNum();
            String constraints = iModifyRoomUI.getConstraint();
            int roomNum = Integer.parseInt(roomInput);
            ArrayList<String> constraintList = new ArrayList<>(Arrays.asList(constraints.split("[\\s]*[,][\\s]*")));
            for (String item : constraintList) {
                if (!item.equalsIgnoreCase("Tech") && (!item.equalsIgnoreCase("Table")) &&
                        (!item.equalsIgnoreCase("Stage")) && (!item.equalsIgnoreCase("None"))) {
                    iModifyRoomUI.modifyRoomError();
                    return;
                }
            }
            if (organizerController.addConstraintToRoom(roomNum, constraintList)) {
                programController.saveForNext();
                iModifyRoomUI.modifyRoomSuccessful();
            } else {
                iModifyRoomUI.modifyRoomError();
            }
        } catch (IllegalArgumentException | NullPointerException e){
            iModifyRoomUI.modifyRoomError();
        }
    }


}
