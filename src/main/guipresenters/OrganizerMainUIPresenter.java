package main.guipresenters;

import com.sun.org.apache.xpath.internal.operations.Or;
import main.controllers.ProgramController;
import main.gateways.Gateway;
import main.gui.*;
import main.guilisteners.*;

public class OrganizerMainUIPresenter implements LogoutButtonListener, UserManagementButtonListener,
        ManageEventRoomButtonListener, RegisteredEventsButtonListener, MessageButtonListener, InboxButtonListener,
        DataManagementButtonListener {

    private OrganizerMainUI organizerMainUI;
    private ProgramController programController;

    public OrganizerMainUIPresenter(ProgramController programController) {
        this.programController = programController;
        this.organizerMainUI = new OrganizerMainUI();
        this.organizerMainUI.addLogoutButtonListener(this);
        this.organizerMainUI.addUserManagementButtonListener(this);
        this.organizerMainUI.addManageEventRoomButtonListener(this);
        this.organizerMainUI.addRegisteredEventsButtonListener(this);
        this.organizerMainUI.addMessageButtonListener(this);
        this.organizerMainUI.addInboxButtonListener(this);
        this.organizerMainUI.addDataManagementButtonListener(this);
    }

    @Override
    public void onDataManagementButtonClicked() {
        GatewayUI gatewayUI = new GatewayUI();
        new GatewayUIPresenter(gatewayUI, this.programController);
        this.organizerMainUI.dispose();
    }

    @Override
    public void onInboxButtonClicked() {
        InboxUI inboxUI = new InboxUI();
        new InboxUIPresenter(inboxUI, this.programController);
        this.organizerMainUI.dispose();
    }

    @Override
    public void onLogoutButtonClicked() {
        LandingUI landingUI = new LandingUI();
        new LandingUIPresenter(landingUI, this.programController);
        this.organizerMainUI.dispose();
    }

    @Override
    public void onManageEventRoomButtonClicked() {
        EventsManagementUI eventsManagementUI = new EventsManagementUI();
        new EventManagementUIPresenter(eventsManagementUI, this.programController);
        this.organizerMainUI.dispose();
    }

    @Override
    public void onMessageButtonClicked() {
        OrganizerMessageUI organizerMessageUI = new OrganizerMessageUI();
        new OrganizerMessageUIPresenter(organizerMessageUI, this.programController);
        this.organizerMainUI.dispose();
    }

    @Override
    public void onRegisteredEventsButtonClicked() {
        //TODO
        this.organizerMainUI.dispose();
    }

    @Override
    public void onUserManagementButtonClicked() {
        UserManagementUI userManagementUI = new UserManagementUI();
        new UserManagementUIPresenter(userManagementUI, this.programController);
        this.organizerMainUI.dispose();
    }
}
