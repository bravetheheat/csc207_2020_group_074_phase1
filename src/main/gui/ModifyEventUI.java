package main.gui;

import main.gui_interface.IModifyEventUI;
import main.guilisteners.*;

import javax.swing.*;
import java.util.ArrayList;

public class ModifyEventUI extends JFrame implements IModifyEventUI {

    private JButton getEventButton;
    private JTextField timeField;
    private JTextField capacityField;
    private JButton backButton;
    private JButton modifySpeakerButton;
    private JButton confirmButton;
    private JPanel panel1;
    private JButton getRoomNumButton;
    private JTextField constraintsField;
    private JLabel constraintLabel;
    private EventsManagementUI eventsManagementUI;
    private BackButtonListener backButtonListener;
    private GetEventsButtonListener getEventsButtonListener;
    private ModifySpeakerButtonListener modifySpeakerButtonListener;
    private ConfirmModifyEventButtonListener confirmModifyEventButtonListener;
    private DeleteAnEventUI deleteAnEventUI;
    private ModifySpeakerUI modifySpeakerUI;
    private SelectRoomButtonListener selectRoomButtonListener;
    private SelectRoomUI selectRoomUI;
    private int eventIndex = -1;

    public ModifyEventUI() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setTitle("Modify An Event");
        this.setContentPane(panel1);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        backButton.addActionListener(e -> backButtonListener.onBackButtonClicked());

        getEventButton.addActionListener(e ->
                getEventsButtonListener.onGetEventsButtonClicked());

        modifySpeakerButton.addActionListener(e ->
                modifySpeakerButtonListener.onModifySpeakerButtonClicked());

        confirmButton.addActionListener(e ->
                confirmModifyEventButtonListener.onConfirmModifyEventButtonClicked());

        getRoomNumButton.addActionListener(e ->
                selectRoomButtonListener.onSelectRoomButtonClicked());
    }

    public String getEventTime() {
        return timeField.getText();
    }

    public String getEventCapacity() {
        return capacityField.getText();
    }

//    public String getRoomNum() {
//        return roomNumField.getText();
//    }

    public String getRoomConstraints() {
        return constraintsField.getText();
    }

    public int getEventIndex() {
        eventIndex = deleteAnEventUI.getEventIndex();
        return eventIndex;
    }

    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    public void addGetEventsButtonListener(GetEventsButtonListener listener) {
        getEventsButtonListener = listener;
    }

    public void addSelectRoomButtonListener(SelectRoomButtonListener listener) {
        selectRoomButtonListener = listener;
    }

    public void addModifySpeakerButtonListener(ModifySpeakerButtonListener listener) {
        modifySpeakerButtonListener = listener;
    }

    public void addConfirmModifyEventButtonListener(ConfirmModifyEventButtonListener listener) {
        confirmModifyEventButtonListener = listener;
    }

    public void modifyEventSuccessful() {
        JOptionPane.showMessageDialog(this,
                "You have successfully modified an event!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void modifyEventError() {
        JOptionPane.showMessageDialog(this,
                "Please try again!",
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    public EventsManagementUI goToEventsManagementUI() {
        eventsManagementUI = new EventsManagementUI();
        this.dispose();
        return eventsManagementUI;
    }

    public DeleteAnEventUI goToDeleteAnEventUI(ArrayList<String> listOfEventsInfo) {
        deleteAnEventUI = new DeleteAnEventUI(listOfEventsInfo);
        this.dispose();
        return deleteAnEventUI;
    }

    public SelectRoomUI goToSelectRoomUI(ArrayList<Integer> listOfRoomInfo, ArrayList<String> constraints) {
        selectRoomUI = new SelectRoomUI(listOfRoomInfo, constraints);
        this.dispose();
        return selectRoomUI;
    }

    public ModifySpeakerUI goToModifySpeakerUI(ArrayList<String> listOfSpeakerInfo,
                                               ArrayList<String> listOfEventSpeakers) {
        modifySpeakerUI = new ModifySpeakerUI(listOfSpeakerInfo, listOfEventSpeakers);
        this.dispose();
        return modifySpeakerUI;
    }
}
