package main.guipresenters;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import main.controllers.AuthController;
import main.controllers.EventController;
import main.controllers.ProgramController;
import main.gui_interface.*;
import main.guilisteners.BackButtonListener;
import main.guilisteners.LoginUIListener;

import java.util.ArrayList;

/**
 * The presenter for <code>LoginUI</code>
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class LoginUIPresenter implements LoginUIListener, BackButtonListener {

    private ProgramController programController;
    private AuthController authController;
    private ILoginUI iLoginUI;
    private ILandingUI iLandingUI;
    private ILoginMessageErrorUI iLoginMessageErrorUI;
    private IAttendeeMainUI iAttendeeMainUI;
    private IOrganizerMainUI iOrganizerMainUI;
    private ISpeakerMainUI iSpeakerMainUI;


    public LoginUIPresenter(ILoginUI loginUI, ProgramController programController) {
        this.iLoginUI = loginUI;
        this.programController = programController;
        this.authController = programController.getAuthController();
        this.iLoginUI.addLoginUIListener(this);
        this.iLoginUI.addBackButtonListener(this);
    }

    @Override
    public void onLoginButtonClicked() {
            String username = iLoginUI.getUserName();
            String password = iLoginUI.getPwd();
            if (username.equals("") || password.equals("")) {
                iLoginMessageErrorUI = iLoginUI.goToLoginMessageErrorUI();
                new LoginMessageErrorPresenter(iLoginMessageErrorUI,
                        programController);
            }
            else if (authController.login(username, password)) {
                String userType = authController.getUserType();
                switch(userType){
                    case "Attendee":
                        programController.saveForNext();
                        iAttendeeMainUI = iLoginUI.goToAttendeeMainUI();
                        new AttendeeMainUIPresenter(iAttendeeMainUI, programController);
                        break;
                    case "Organizer":
                        programController.saveForNext();
                        iOrganizerMainUI = iLoginUI.goToOrganizerMainUI();
                        new OrganizerMainUIPresenter(iOrganizerMainUI, programController);
                        break;
                    case "Speaker":
                        programController.saveForNext();
                        EventController eventController = this.programController.getEventController();
                        ArrayList<String> eventIDs = eventController.getSpeakerEvents(
                                this.authController.fetchLoggedInUser());
                        ArrayList<String> eventInfo = new ArrayList<>();
                        for (String id:eventIDs) {
                            eventInfo.add(eventController.getSingleEventInfo(id));
                        }
                        iSpeakerMainUI = iLoginUI.goToSpeakerMainUI(eventInfo);
                        new SpeakerMainUIPresenter(iSpeakerMainUI, programController);
                        break;
                    default:
                        iLoginMessageErrorUI = iLoginUI.goToLoginMessageErrorUI();
                        new LoginMessageErrorPresenter(iLoginMessageErrorUI,
                                programController);
                        break;
                }
            }
            else {
                iLoginMessageErrorUI = iLoginUI.goToLoginMessageErrorUI();
                new LoginMessageErrorPresenter(iLoginMessageErrorUI,
                        programController);
            }
    }

    @Override
    public void onBackButtonClicked() {
        this.iLandingUI = iLoginUI.goToLandingUI();
        new LandingUIPresenter(this.iLandingUI, programController);
    }
}
