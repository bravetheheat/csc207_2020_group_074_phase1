package main.guipresenters;

import main.controllers.EventController;
import main.controllers.OrganizerController;
import main.controllers.ProgramController;
import main.gui_interface.IDeleteAnEventUI;
import main.gui_interface.IModifyEventUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.DeleteEventButtonListener;
import main.guilisteners.SelectEventButtonListener;

public class DeleteAnEventUIPresenter
        implements BackButtonListener, DeleteEventButtonListener, SelectEventButtonListener {

    private ProgramController programController;
    private OrganizerController organizerController;
    private EventController eventController;
    private IDeleteAnEventUI iDeleteAnEventUI;
    private IModifyEventUI iModifyEventUI;

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

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iModifyEventUI = iDeleteAnEventUI.goToModifyEventUI();
        new ModifyEventUIPresenter(iModifyEventUI, programController);
    }

    @Override
    public void onDeleteEventButtonClicked() {
        if (eventController.getAllEvents().size() > 0) {
            int eventIndex = iDeleteAnEventUI.getEventIndexFromList();
            String eventId = eventController.getEventId(eventIndex);
            if (organizerController.removeEvent(eventId)) {
                iDeleteAnEventUI.deleteEventSuccessful();
            }
        }
        else {
            iDeleteAnEventUI.deleteEventError();
        }
    }

    @Override
    public int onSelectEventButtonClicked() {
        programController.saveForNext();
        iModifyEventUI = iDeleteAnEventUI.goToModifyEventUI();
        new ModifyEventUIPresenter(iModifyEventUI, programController);
        return iDeleteAnEventUI.getEventIndexFromList();
    }
}
