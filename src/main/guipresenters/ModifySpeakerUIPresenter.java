package main.guipresenters;

import main.controllers.EventController;
import main.controllers.OrganizerController;
import main.controllers.ProgramController;
import main.gui_interface.IModifyEventUI;
import main.gui_interface.IModifySpeakerUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ConfirmSelectSpeakerButtonListener;
import main.guilisteners.DeleteSpeakerButtonListener;

import java.util.ArrayList;
import java.util.List;

public class ModifySpeakerUIPresenter
        implements BackButtonListener, ConfirmSelectSpeakerButtonListener, DeleteSpeakerButtonListener {

    ProgramController programController;
    OrganizerController organizerController;
    EventController eventController;
    private IModifySpeakerUI iModifySpeakerUI;
    private IModifyEventUI iModifyEventUI;
    private int eventIndex = -1;

    public ModifySpeakerUIPresenter(IModifySpeakerUI modifySpeakerUI,
                                    ProgramController programController) {
        this.iModifySpeakerUI = modifySpeakerUI;
        this.programController = programController;
        this.organizerController = new OrganizerController(programController);
        this.eventController = organizerController.getEventController();
        iModifySpeakerUI.addBackButtonListener(this);
        iModifySpeakerUI.addConfirmSelectSpeakerButtonListener(this);
        iModifySpeakerUI.addDeleteSpeakerButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iModifyEventUI = iModifySpeakerUI.goToModifyEventUI();
        iModifyEventUI.storeEventIndex(this.eventIndex);
        new ModifyEventUIPresenter(iModifyEventUI, programController);
    }

    @Override
    public void onConfirmSelectSpeakerButtonClicked() {
        this.eventIndex = iModifySpeakerUI.getEventIndex();
        String eventId;
        if (eventController.getAllEvents().size() == 0 || eventIndex < 0) {
            System.out.println("error1, event index: " + eventIndex);
            iModifySpeakerUI.modifySpeakerError();
            return;
        }
        else {
            eventId = eventController.getEventId(eventIndex);
        }
        List<String> speakers = organizerController.getAllSpeakers();
        int[] speakerIndices = iModifySpeakerUI.getSpeakerIndices();
        if (speakerIndices.length == 0) {
            iModifySpeakerUI.modifySpeakerError();
        }
        else if (eventController.getEventType(eventId).equals("NoSpeakerEvent")) {
            iModifySpeakerUI.modifySpeakerError();
        }
        else if (speakerIndices.length == 1 &&
                eventController.getEventType(eventId).equals("OneSpeakerEvent")) {
            String speakerId = speakers.get(speakerIndices[0]);
            System.out.println("One speaker event, speaker id: " + speakerId);
            if (organizerController.updateSingleEventSpeaker(eventId, speakerId)) {
                iModifySpeakerUI.modifySpeakerSuccessful();
            }
            else {
                System.out.println("error 2, One speaker event, speaker id: " + speakerId);
                iModifySpeakerUI.modifySpeakerError();
            }
        }
        else if (eventController.getEventType(eventId).equals("MultiSpeakerEvent")) {
            ArrayList<String> newSpeakerList = new ArrayList<>();
            for (int index : speakerIndices) {
                newSpeakerList.add(speakers.get(index));
            }
            for (String id : newSpeakerList) {
                if (!organizerController.addSpeakerMultiEvent(eventId, id)) {
                    iModifySpeakerUI.modifySpeakerError();
                    return;
                }
            }
            iModifySpeakerUI.modifySpeakerSuccessful();
        }
    }

    @Override
    public void onDeleteSpeakerButtonClicked() {
        eventIndex = iModifySpeakerUI.getEventIndex();
        int eventSpeakerIndex = iModifySpeakerUI.getEventSpeakerIndex();
        String eventId;
        if (eventController.getAllEvents().size() == 0 || eventIndex <= 0 ||
                eventSpeakerIndex < 0) {
            iModifySpeakerUI.modifySpeakerError();
            return;
        }
        else {
            eventId = eventController.getEventId(eventIndex);
        }
        if (!eventController.getEventType(eventId).equals("MultiSpeakerEvent")) {
            iModifySpeakerUI.modifySpeakerError();
        }
        else {
            List<String> eventSpeakers = eventController.getEventSpeakers(eventId);
            String speakerId = eventSpeakers.get(eventSpeakerIndex);
            if (organizerController.removeSpeakerMultiEvent(eventId, speakerId)) {
                programController.saveForNext();
                iModifyEventUI = iModifySpeakerUI.goToModifyEventUI();
                iModifyEventUI.storeEventIndex(eventIndex);
                new ModifyEventUIPresenter(iModifyEventUI, programController);
            }
            else {
                iModifySpeakerUI.modifySpeakerError();
            }
        }

    }
}
