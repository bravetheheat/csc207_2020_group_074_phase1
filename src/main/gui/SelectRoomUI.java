package main.gui;

import main.gui_interface.ISelectRoomUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ConfirmSelectRoomButtonListener;

import javax.swing.*;
import java.util.ArrayList;

public class SelectRoomUI extends JFrame implements ISelectRoomUI {

    private JPanel jPanel;
    private JList<Object> roomList;
    private JScrollPane jScrollPane;
    private JButton backButton;
    private JButton selectButton;
    private BackButtonListener backButtonListener;
    private CreateEventUI createEventUI;
    private ConfirmSelectRoomButtonListener confirmSelectRoomButtonListener;
    int roomNum;

    public SelectRoomUI(ArrayList<Integer> listOfRoomInfo, ArrayList<String> constraints) {
        jPanel = new JPanel();

        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // list of users
        roomList = new JList<>(listOfRoomInfo.toArray());

        roomList.setVisibleRowCount(20);
        jScrollPane = new JScrollPane(roomList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jPanel.add(jScrollPane);

        // select button
        selectButton = new JButton("Select");
        jPanel.add(selectButton);

        // back button
        backButton = new JButton("Back");
        jPanel.add(backButton);

        selectButton.addActionListener(e -> notifyListenerOnSelectButtonClicked(
                constraints));
        backButton.addActionListener(e -> notifyListenerOnBackButtonClicked());

        add(jPanel);
        setVisible(true);
    }

    public int getRoomIndex() {
        return roomList.getSelectedIndex();
    }

    public int getRoomNum() {
        return this.roomNum;
    }

    public void notifyListenerOnBackButtonClicked() {
        backButtonListener.onBackButtonClicked();
    }

    @Override
    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    public void notifyListenerOnSelectButtonClicked(ArrayList<String> category) {
        roomNum = confirmSelectRoomButtonListener.onConfirmSelectRoomButtonClicked(category);
    }

    public void addSelectButtonListener(ConfirmSelectRoomButtonListener listener) {
        confirmSelectRoomButtonListener = listener;
    }

    public void selectRoomSuccessful() {
        JOptionPane.showMessageDialog(this,
                "You have selected a room.",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void selectRoomError() {
        JOptionPane.showMessageDialog(this,
                "Please try again!",
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public CreateEventUI goToCreateEventUI() {
        createEventUI = new CreateEventUI();
        this.dispose();
        return createEventUI;
    }
}
