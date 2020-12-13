package main.gui;

import main.controllers.ProgramController;
import main.gui_interface.IEventsManagementUI;
import main.guilisteners.*;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The user interface for managing events (organizer only)
 *
 * @author Steven Yuan
 */
public class EventsManagementUI extends JFrame implements IEventsManagementUI {

    private JButton backButton;
    private JPanel panel1;
    private JButton createRoomButton;
    private JButton createEventButton;
    private JButton modifyEventButton;
    private JButton modifyRoomButton;
    private JButton seeScheduleButton;
    private JButton seeRoomsButton;
    private ProgramController programController;
    private BackButtonListener backButtonListener;
    private CreateRoomButtonListener createRoomButtonListener;
    private CreateRoomUI createRoomUI;
    private OrganizerMainUI organizerMainUI;
    private ModifyRoomButtonListener modifyRoomButtonListener;
    private ModifyRoomUI modifyRoomUI;
    private CreateEventButtonListener createEventButtonListener;
    private CreateEventUI createEventUI;
    private ModifyEventButtonListener modifyEventButtonListener;
    private ModifyEventUI modifyEventUI;
    private SeeRoomsButtonListener seeRoomsButtonListener;
    private SeeRoomsUI seeRoomsUI;
    private SeeScheduleUI seeScheduleUI;
    private SeeScheduleButtonListener seeScheduleButtonListener;

    public EventsManagementUI() {
        this.setTitle("Manage Events");
        this.setSize(600, 500);
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        backButton.addActionListener(e -> notifyListenerOnBackButtonClicked());

        createRoomButton.addActionListener(e -> notifyListenerOnCreateRoomButtonClicked());

        modifyRoomButton.addActionListener(e -> notifyListenerOnModifyRoomButtonClicked());

        createEventButton.addActionListener(e -> notifyListenerOnCreateEventButtonClicked());

        modifyEventButton.addActionListener(e -> notifyListenerOnModifyEventButtonClicked());

        seeRoomsButton.addActionListener(e -> notifyListenerOnSeeRoomsButtonClicked());

        seeScheduleButton.addActionListener(e -> notifyListenerOnSeeScheduleButtonClicked());
    }

    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    public void addCreateRoomButtonListener(CreateRoomButtonListener listener) {
        createRoomButtonListener = listener;
    }

    public void addModifyRoomButtonListener(ModifyRoomButtonListener listener) {
        modifyRoomButtonListener = listener;
    }

    public void addCreateEventButtonListener(CreateEventButtonListener listener) {
        createEventButtonListener = listener;
    }

    public void addModifyEventButtonListener(ModifyEventButtonListener listener) {
        modifyEventButtonListener = listener;
    }

    public void addSeeRoomsButtonListener(SeeRoomsButtonListener listener) {
        seeRoomsButtonListener = listener;
    }

    public void addSeeScheduleButtonListener(SeeScheduleButtonListener listener) {
        seeScheduleButtonListener = listener;
    }

    public void notifyListenerOnBackButtonClicked() {
        backButtonListener.onBackButtonClicked();
    }

    public void notifyListenerOnCreateRoomButtonClicked() {
        createRoomButtonListener.onCreateRoomButtonClicked();
    }

    public void notifyListenerOnModifyRoomButtonClicked() {
        modifyRoomButtonListener.onModifyRoomButtonClicked();
    }

    public void notifyListenerOnCreateEventButtonClicked() {
        createEventButtonListener.onCreateEventButtonClicked();
    }

    public void notifyListenerOnModifyEventButtonClicked() {
        modifyEventButtonListener.onModifyEventButtonClicked();
    }

    public void notifyListenerOnSeeRoomsButtonClicked() {
        seeRoomsButtonListener.onSeeRoomsButtonClicked();
    }

    public void notifyListenerOnSeeScheduleButtonClicked() {
        seeScheduleButtonListener.onSeeScheduleButtonClicked();
    }

    public OrganizerMainUI goToOrganizerMainUI() {
        organizerMainUI = new OrganizerMainUI();
        this.dispose();
        return organizerMainUI;
    }

    public CreateRoomUI goToCreateRoomUI() {
        createRoomUI = new CreateRoomUI();
        this.dispose();
        return createRoomUI;
    }

    public ModifyRoomUI goToModifyRoomUI() {
        modifyRoomUI = new ModifyRoomUI();
        this.dispose();
        return modifyRoomUI;
    }

    public CreateEventUI goToCreateEventUI() {
        createEventUI = new CreateEventUI();
        this.dispose();
        return createEventUI;
    }

    public ModifyEventUI goToModifyEventUI() {
        modifyEventUI = new ModifyEventUI();
        this.dispose();
        return modifyEventUI;
    }

    public SeeRoomsUI goToSeeRoomsUI(ArrayList<String> listOfRoomsInfo) {
        seeRoomsUI = new SeeRoomsUI(listOfRoomsInfo);
        this.dispose();
        return seeRoomsUI;
    }

    public SeeScheduleUI goToSeeScheduleUI(ArrayList<String> listOfEventInfo) {
        seeScheduleUI = new SeeScheduleUI(listOfEventInfo);
        this.dispose();
        return seeScheduleUI;
    }

}
