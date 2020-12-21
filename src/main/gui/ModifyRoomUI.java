package main.gui;

import main.gui_interface.IModifyRoomUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ConfirmModifyRoomButtonListener;

import javax.swing.*;

/**
 * UI class for modifying a room
 *
 * @see EventsManagementUI
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class ModifyRoomUI extends JFrame implements IModifyRoomUI {

    private JTextField roomNumField;
    private JTextField constraintsField;
    private JButton backButton;
    private JLabel roomNumLabel;
    private JLabel constraintLabel;
    private JLabel optionLabel;
    private JPanel panel1;
    private JButton confirmModifyRoomButton;
    private BackButtonListener backButtonListener;
    private ConfirmModifyRoomButtonListener confirmModifyRoomButtonListener;
    private EventsManagementUI eventsManagementUI;

    public ModifyRoomUI() {
        this.setTitle("Modify Room");
        this.setSize(600, 500);
        this.setContentPane(panel1);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        backButton.addActionListener(e -> notifyListenerOnBackButtonClicked());


        confirmModifyRoomButton.addActionListener(e -> notifyListenerOnConfirmButtonClicked());
    }

    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    public void notifyListenerOnBackButtonClicked() {
        backButtonListener.onBackButtonClicked();
    }

    public void addConfirmButtonListener(ConfirmModifyRoomButtonListener listener) {
        confirmModifyRoomButtonListener = listener;
    }

    public void notifyListenerOnConfirmButtonClicked() {
        confirmModifyRoomButtonListener.onConfirmModifyRoomButtonClicked();
    }

    public String getRoomNum() {
        return roomNumField.getText();
    }

    public String getConstraint() {
        return constraintsField.getText();
    }

    public void modifyRoomSuccessful() {
        JOptionPane.showMessageDialog(this,
                "Successful Modification!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void modifyRoomError() {
        JOptionPane.showMessageDialog(this,
                "Please try again!",
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    public EventsManagementUI goToEventsManagementUI() {
        eventsManagementUI = new EventsManagementUI();
        this.dispose();
        return eventsManagementUI;
    }
}
