package main.gui;

import main.gui_interface.ICreateRoomUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ConfirmCreateRoomButtonListener;

import javax.swing.*;

/**
 * UI class for creating a room
 *
 * @see EventsManagementUI
 * @author Steven Yuan
 */
public class CreateRoomUI extends JFrame implements ICreateRoomUI {
    private JPanel panel1;
    private JTextField roomNumberField;
    private JTextField capacityField;
    private JLabel roomNumberLabel;
    private JLabel capacityLabel;
    private JButton confirmCreateRoomButton;
    private JButton backButton;
    private BackButtonListener backButtonListener;
    private ConfirmCreateRoomButtonListener confirmCreateRoomButtonListener;
    private EventsManagementUI eventsManagementUI;

    public CreateRoomUI() {
        this.setTitle("Create Room");
        this.setSize(600, 500);
        this.setContentPane(panel1);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        backButton.addActionListener(e -> notifyListenerOnBackButtonClicked());

        confirmCreateRoomButton.addActionListener(
                e -> notifyListenerOnConfirmButtonClicked());
    }

    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    public void notifyListenerOnBackButtonClicked() {
        backButtonListener.onBackButtonClicked();
    }

    public void addConfirmButtonListener(ConfirmCreateRoomButtonListener listener) {
        confirmCreateRoomButtonListener = listener;
    }

    public void notifyListenerOnConfirmButtonClicked() {
        confirmCreateRoomButtonListener.onConfirmCreateRoomButtonClicked();
    }

    public String getRoomNum() {
        return roomNumberField.getText();
    }

    public String getCapacity() {
        return capacityField.getText();
    }

    public void createNewRoomSuccessful() {
        JOptionPane.showMessageDialog(this,
                "You have successfully created a new room!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void createNewRoomError() {
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
