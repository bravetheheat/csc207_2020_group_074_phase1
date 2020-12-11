package main.gui;

import main.gui_interface.IViewUserEventsUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.CancelEventButtonListener;

import javax.swing.*;
import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")

public class ViewUserEventsUI extends JFrame implements IViewUserEventsUI {

    private JPanel jPanel;
    private JButton cancelEventButton;
    private JList<Object> listOfEvents;
    private JScrollPane jScrollPane;
    private JButton backButton;
    private BackButtonListener backButtonListener;
    private CancelEventButtonListener cancelEventButtonListener;
    private EventSignUpUI eventSignUpUI;

    public ViewUserEventsUI(ArrayList<String> listOfUserEvents) {
        this.jPanel = new JPanel();

        this.setTitle("Your Events");
        this.setSize(600, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        listOfEvents = new JList<>(listOfUserEvents.toArray());
        listOfEvents.setVisibleRowCount(20);
        jScrollPane = new JScrollPane(listOfEvents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jPanel.add(jScrollPane);

        cancelEventButton = new JButton("Cancel Event");
        jPanel.add(cancelEventButton);

        backButton = new JButton("Back");
        jPanel.add(backButton);

        backButton.addActionListener(e -> notifyListenerBackButtonClicked());

        cancelEventButton.addActionListener(
                e -> notifyListenerCancelEventButtonClicked());

        this.add(jPanel);
        this.setVisible(true);
    }

    @Override
    public int getEventIndexFromList() {
        return listOfEvents.getSelectedIndex();
    }

    public void notifyListenerCancelEventButtonClicked() {
        cancelEventButtonListener.onCancelEventButtonClicked();
    }

    public void notifyListenerBackButtonClicked() {
        backButtonListener.onBackButtonClicked();
    }

    @Override
    public void addRegisterEventButtonListener(CancelEventButtonListener listener) {
        cancelEventButtonListener = listener;
    }

    @Override
    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    @Override
    public void cancelNewEventSuccessful() {
        JOptionPane.showMessageDialog(this,
                "Event Cancelled!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void cancelNewEventError() {
        JOptionPane.showMessageDialog(this,
                "What are you trying to do?",
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public EventSignUpUI goToEventSignUpUI() {
        eventSignUpUI = new EventSignUpUI();
        this.dispose();
        return eventSignUpUI;
    }
}
