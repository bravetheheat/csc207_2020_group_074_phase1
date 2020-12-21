package main.guipresenters;

import main.controllers.EventController;
import main.controllers.OrganizerController;
import main.controllers.ProgramController;
import main.gui_interface.IDeleteAnEventUI;
import main.gui_interface.IModifyEventUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.DeleteEventButtonListener;
import main.guilisteners.SelectEventButtonListener;

/**
 * Presenter class for deleting an event or selecting an event for future modification
 *
 * @author Steven Yuan
 */
public class DeleteAnEventUIPresenter
        implements BackButtonListener, DeleteEventButtonListener, SelectEventButtonListener {

    private ProgramController programController;
    private OrganizerController organizerController;
    private EventController eventController;
    private IDeleteAnEventUI iDeleteAnEventUI;
    private IModifyEventUI iModifyEventUI;
    private int eventIndex = -1;

    public DeleteAnEventUIPresenter(IDeleteAnEventUI deleteAnEventUI,
                                    ProgramController programController) {
        iDeleteAnEventUI = deleteAnEventUI;
        this.programController = programController;
        this.organizerController = new OrganizerController(programController);
        this.eventController = programController.getEventController();
        iDeleteAnEventUI.addBackButtonListener(this);
        iDeleteAnEventUI.addDeleteEventButtonListener(this);
        iDeleteAnEventUI.addSelectEventButtonListener(this);
    }

    /**
     * Go to the previous frame
     */
    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iModifyEventUI = iDeleteAnEventUI.goToModifyEventUI();
        new ModifyEventUIPresenter(iModifyEventUI, programController);
    }

    /**
     * Delete an event
     */
    @Override
    public void onDeleteEventButtonClicked() {
        if (eventController.getAllEvents().size() > 0) {
            int eventIndex = iDeleteAnEventUI.getEventIndexFromList();
            String eventId = eventController.getEventId(eventIndex);
            if (organizerController.removeEvent(eventId)) {
                programController.saveForNext();
                iModifyEventUI = iDeleteAnEventUI.goToModifyEventUI();
                new ModifyEventUIPresenter(iModifyEventUI, programController);
            }
        }
        else {
            iDeleteAnEventUI.deleteEventError();
        }
    }

    /**
     * Select an event for future modification
     * @return an event index
     */
    @Override
    public int onSelectEventButtonClicked() {
        programController.saveForNext();
        eventIndex = iDeleteAnEventUI.getEventIndexFromList();
        if (eventController.getAllEvents().size() > 0) {
            iModifyEventUI = iDeleteAnEventUI.goToModifyEventUI();
            iModifyEventUI.storeEventIndex(eventIndex);
            new ModifyEventUIPresenter(iModifyEventUI, programController);
            return iDeleteAnEventUI.getEventIndexFromList();
        }
        else {
            iDeleteAnEventUI.deleteEventError();
            return -1;
        }
    }
}
