package main.gui;

import main.gui_interface.ISeeRoomsUI;
import main.guilisteners.BackButtonListener;

import javax.swing.*;
import java.util.ArrayList;

public class SeeRoomsUI extends JFrame implements ISeeRoomsUI {
    private JPanel jPanel;
    private JList<Object> listOfEvents;
    private JScrollPane jScrollPane;
    private JButton backButton;
    private BackButtonListener backButtonListener;
    private EventsManagementUI eventsManagementUI;

    public SeeRoomsUI(ArrayList<Integer> listOfRoomsInfo) {
        this.jPanel = new JPanel();

        this.setTitle("Available Rooms");
        this.setSize(600, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        listOfEvents = new JList<>(listOfRoomsInfo.toArray());
        listOfEvents.setVisibleRowCount(20);
        jScrollPane = new JScrollPane(listOfEvents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
