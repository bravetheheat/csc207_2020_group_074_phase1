package main.gui;

import main.gui_interface.ISeeRoomsUI;
import main.guilisteners.BackButtonListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * UI class for viewing available rooms
 *
 * @see EventsManagementUI
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class SeeRoomsUI extends JFrame implements ISeeRoomsUI {
    private JPanel jPanel;
    private JList<Object> listOfRooms;
    private JScrollPane jScrollPane;
    private JButton backButton;
    private BackButtonListener backButtonListener;
    private EventsManagementUI eventsManagementUI;

    public SeeRoomsUI(ArrayList<String> listOfRoomsInfo) {
        this.jPanel = new JPanel();

        this.setTitle("Available Rooms");
        this.setSize(600, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        listOfRooms = new JList<>(listOfRoomsInfo.toArray());
        listOfRooms.setVisibleRowCount(20);
        jScrollPane = new JScrollPane(listOfRooms, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setPreferredSize(new Dimension(550, 400));
        jScrollPane.setViewportView(listOfRooms);
        jPanel.add(jScrollPane);


        backButton = new JButton("Back");
        jPanel.add(backButton);

        this.add(jPanel);
        this.setVisible(true);

        backButton.addActionListener(e -> backButtonListener.onBackButtonClicked());
    }

    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    public EventsManagementUI goToEventsManagementUI() {
        eventsManagementUI = new EventsManagementUI();
        this.dispose();
        return eventsManagementUI;
    }
}
