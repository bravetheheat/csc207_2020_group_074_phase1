package main.guipresenters;

import main.controllers.EventController;
import main.controllers.OrganizerController;
import main.controllers.ProgramController;
import main.gui_interface.*;
import main.guilisteners.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModifyEventUIPresenter implements BackButtonListener, GetEventsButtonListener,
        ModifySpeakerButtonListener, ConfirmModifyEventButtonListener, SelectRoomButtonListener {

    private ProgramController programController;
    private EventController eventController;
    private OrganizerController organizerController;
    private IModifyEventUI iModifyEventUI;
    private IEventsManagementUI iEventsManagementUI;
    private IModifySpeakerUI iModifySpeakerUI;
    private IDeleteAnEventUI iDeleteAnEventUI;
    private ISelectRoomUI iSelectRoomUI;

    public ModifyEventUIPresenter(IModifyEventUI modifyEventUI,
                                  ProgramController programController) {
        iModifyEventUI = modifyEventUI;
        this.programController = programController;
        this.eventController = programController.getEventController();
        this.organizerController = new OrganizerController(programController);
        iModifyEventUI.addBackButtonListener(this);
        iModifyEventUI.addGetEventsButtonListener(this);
        iModifyEventUI.addModifySpeakerButtonListener(this);
        iModifyEventUI.addConfirmModifyEventButtonListener(this);
        iModifyEventUI.addSelectRoomButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iEventsManagementUI = iModifyEventUI.goToEventsManagementUI();
        new EventsManagementUIPresenter(iEventsManagementUI, programController);
    }

    @Override
    public void onConfirmModifyEventButtonClicked() {
        if (iDeleteAnEventUI.getEventIndex() >= 0) {
            int eventIndex = iDeleteAnEventUI.getEventIndex();
            String eventId = eventController.getEventId(eventIndex);
            String dateStr = iModifyEventUI.getEventTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
            int roomNum = eventController.getRoomNum(eventId);
        }
    }

    @Override
    public void onGetEventsButtonClicked() {
        programController.saveForNext();
        iDeleteAnEventUI = iModifyEventUI.goToDeleteAnEventUI(
                eventController.getEventsInfoList());
        new DeleteAnEventUIPresenter(iDeleteAnEventUI, programController);
    }

    @Override
    public void onModifySpeakerButtonClicked() {
        programController.saveForNext();
        iModifySpeakerUI = iModifyEventUI.goToModifySpeakerUI(
                (ArrayList<String>) organizerController.getAllSpeakers());
        new ModifySpeakerUIPresenter(iModifySpeakerUI, programController);
    }

    @Override
    public void onSelectRoomButtonClicked() {
        programController.saveForNext();
        String constraints = iModifyEventUI.getRoomConstraints();
        ArrayList<String> category = new ArrayList<>(Arrays.asList(constraints.split("[\\s]*[,][\\s]*")));
        for (String item : category) {
            if (!item.equalsIgnoreCase("Tech") && (!item.equalsIgnoreCase("Table")) &&
                    (!item.equalsIgnoreCase("Stage")) && (!item.equalsIgnoreCase("None"))) {
                iModifyEventUI.modifyEventError();
                return;
            }
        }
        List<Integer> rooms = organizerController.getEventController().
                getSuggestedRooms(category);
        iSelectRoomUI = iModifyEventUI.goToSelectRoomUI((ArrayList<Integer>) rooms, category);
        new SelectRoomUIPresenter(iSelectRoomUI, programController);
    }
}
