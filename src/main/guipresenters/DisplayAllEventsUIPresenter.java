package main.guipresenters;

import main.controllers.AuthController;
import main.controllers.EventController;
import main.controllers.ProgramController;
import main.gui_interface.IDisplayAllEventsUI;
import main.gui_interface.IEventSignUpUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.RegisterEventButtonListener;

@SuppressWarnings("FieldCanBeLocal")

public class DisplayAllEventsUIPresenter
        implements BackButtonListener, RegisterEventButtonListener {

    private ProgramController programController;
    private AuthController authController;
    private EventController eventController;
    private IDisplayAllEventsUI iDisplayAllEventsUI;
    private IEventSignUpUI iEventSignUpUI;

    public DisplayAllEventsUIPresenter(IDisplayAllEventsUI displayAllEventsUI,
                                       ProgramController programController) {
        this.iDisplayAllEventsUI = displayAllEventsUI;
        this.programController = programController;
        this.authController = programController.getAuthController();
        this.eventController = programController.getEventController();
        this.iDisplayAllEventsUI.addBackButtonListener(this);
        this.iDisplayAllEventsUI.addRegisterEventButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iEventSignUpUI = iDisplayAllEventsUI.goToEventSignUpUI();
        new EventSignUpUIPresenter(iEventSignUpUI, programController);
    }

    @Override
    public void onRegisterEventButtonClicked() {
        String userId = authController.fetchLoggedInUser();
        try {
            if (eventController.getAllEvents().size() > 0) {
                int eventIndex = iDisplayAllEventsUI.getEventIndexFromList();
                String eventId = eventController.getEventId(eventIndex);
                if (eventController.signupEvent(eventId, userId)) {
                    iDisplayAllEventsUI.registerNewEventSuccessful();
                } else {
                    iDisplayAllEventsUI.registerNewEventError();
                }
            }
            else {
                iDisplayAllEventsUI.registerNewEventError();
            }
        } catch (NullPointerException | IllegalArgumentException |
                IndexOutOfBoundsException e) {
            iDisplayAllEventsUI.registerNewEventError();
        }
    }
}
