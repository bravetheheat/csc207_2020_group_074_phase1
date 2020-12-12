package main.gui;

import main.gui_interface.ICreateEventUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ConfirmCreateEventButtonListener;
import main.guilisteners.SelectRoomButtonListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateEventUI extends JFrame implements ICreateEventUI {

    private JPanel panel1;
    private JTextField titleField;
    private JTextField typeField;
    private JTextField constraintField;
    private JLabel titleLabel;
    private JButton selectRoomButton;
    private JButton confirmButton;
    private JButton backButton;
    private JTextField durationField;
    private JLabel durationLabel;
    private JTextField capacityField;
    private JLabel capacityLabel;
    private JTextField dateField;
    private JLabel dateLabel;
    private BackButtonListener backButtonListener;
    private SelectRoomButtonListener selectRoomButtonListener;
    private ConfirmCreateEventButtonListener confirmCreateEventButtonListener;
    private EventsManagementUI eventsManagementUI;
    private SelectRoomUI selectRoomUI;

    public CreateEventUI() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setTitle("Create An Event");
        this.setContentPane(panel1);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        backButton.addActionListener(e -> notifyListenerOnBackButtonClicked());

        selectRoomButton.addActionListener(e -> notifyListenerOnSelectRoomButtonClicked());

        confirmButton.addActionListener(e -> notifyListenerOnConfirmButtonClicked());
    }

    public String getEventTitle() {
        return titleField.getText();
    }

    public String getEventType() {
        return typeField.getText();
    }

    public ArrayList<String> getEventConstraints() {
        String constraintString = constraintField.getText();
        ArrayList<String> category = new ArrayList<>(Arrays.asList(constraintString.split("[\\s]*[,][\\s]*")));
        return category;
    }

    public String getEventDuration() {
        return durationField.getText();
    }

    public String getEventCapacity() {
        return capacityField.getText();
    }

    public String getEventDate() {
        return dateField.getText();
    }

    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    public void addSelectRoomButtonListener(SelectRoomButtonListener listener) {
        selectRoomButtonListener = listener;
    }

    public void addConfirmButtonListener(ConfirmCreateEventButtonListener listener) {
        confirmCreateEventButtonListener = listener;
    }

    public void notifyListenerOnBackButtonClicked() {
        backButtonListener.onBackButtonClicked();
    }

    public void notifyListenerOnSelectRoomButtonClicked() {
        selectRoomButtonListener.onSelectRoomButtonClicked();
    }

    public void notifyListenerOnConfirmButtonClicked() {
        confirmCreateEventButtonListener.onConfirmCreateEventButtonClicked();
    }

    public void createNewEventSuccessful() {
        JOptionPane.showMessageDialog(this,
                "You have successfully created a new event!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void createNewEventError() {
        JOptionPane.showMessageDialog(this,
                "Please try again!",
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    public SelectRoomUI goToSelectRoomUI(ArrayList<Integer> listOfRoomInfo) {
        selectRoomUI = new SelectRoomUI(listOfRoomInfo, this.getEventConstraints());
        this.dispose();
        return selectRoomUI;
    }

    public EventsManagementUI goToEventsManagementUI() {
        eventsManagementUI = new EventsManagementUI();
        this.dispose();
        return eventsManagementUI;
    }
}
