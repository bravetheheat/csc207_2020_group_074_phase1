package main.guipresenters;

import main.controllers.OrganizerController;
import main.controllers.ProgramController;
import main.gui_interface.ICreateRoomUI;
import main.gui_interface.IEventsManagementUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ConfirmCreateRoomButtonListener;

public class CreateRoomUIPresenter
        implements BackButtonListener, ConfirmCreateRoomButtonListener {

    private ProgramController programController;
    private OrganizerController organizerController;
    private ICreateRoomUI iCreateRoomUI;
    private IEventsManagementUI iEventsManagementUI;

    public CreateRoomUIPresenter(ICreateRoomUI createRoomUI,
                                 ProgramController programController) {
        this.programController = programController;
        organizerController = new OrganizerController(programController);
        this.iCreateRoomUI = createRoomUI;
        iCreateRoomUI.addBackButtonListener(this);
        iCreateRoomUI.addConfirmButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iEventsManagementUI = iCreateRoomUI.goToEventsManagementUI();
        new EventsManagementUIPresenter(iEventsManagementUI, programController);
    }

    @Override
    public void onConfirmCreateRoomButtonClicked() {
        String roomNum = iCreateRoomUI.getRoomNum();
        String capacity = iCreateRoomUI.getCapacity();
        try {
            boolean success = organizerController.createRoom(Integer.parseInt(roomNum),
                    Integer.parseInt(capacity));
            if (success) {
                iCreateRoomUI.createNewRoomSuccessful();
            }
            else {
                iCreateRoomUI.createNewRoomError();
            }
        } catch (IllegalArgumentException e) {
            iCreateRoomUI.createNewRoomError();
        }
    }
}
