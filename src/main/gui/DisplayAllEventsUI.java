package main.gui;

import main.gui_interface.IDisplayAllEventsUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.RegisterEventButtonListener;

import javax.swing.*;
import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")

public class DisplayAllEventsUI extends JFrame implements IDisplayAllEventsUI {

    private JPanel jPanel;
    private JButton registerEventButton;
    private JList<Object> listOfEvents;
    private JScrollPane jScrollPane;
    private JButton backButton;
    private BackButtonListener backButtonListener;
    private RegisterEventButtonListener registerEventButtonListener;
    private EventSignUpUI eventSignUpUI;

    public DisplayAllEventsUI(ArrayList<String> listOfEventsInfo) {
        this.jPanel = new JPanel();

        this.setTitle("Available Events");
        this.setSize(900, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        listOfEvents = new JList<>(listOfEventsInfo.toArray());
        listOfEvents.setVisibleRowCount(20);
        jScrollPane = new JScrollPane(listOfEvents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jPanel.add(jScrollPane);

        registerEventButton = new JButton("Sign Up");
        jPanel.add(registerEventButton);

        backButton = new JButton("Back");
        jPanel.add(backButton);

        this.add(jPanel);
        this.setVisible(true);

        backButton.addActionListener(e -> notifyListenerBackButtonClicked());

        registerEventButton.addActionListener(
                e -> notifyListenerRegisterEventButtonClicked());
    }

    @Override
    public int getEventIndexFromList() {
        return listOfEvents.getSelectedIndex();
    }

    @Override
    public void addRegisterEventButtonListener(RegisterEventButtonListener listener) {
        registerEventButtonListener = listener;
    }

    @Override
    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    public void notifyListenerRegisterEventButtonClicked() {
        registerEventButtonListener.onRegisterEventButtonClicked();
    }

    public void notifyListenerBackButtonClicked() {
        backButtonListener.onBackButtonClicked();
    }

    public void registerNewEventSuccessful() {
        JOptionPane.showMessageDialog(this,
                "You have successfully signed up for this event!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void registerNewEventError() {
        JOptionPane.showMessageDialog(this,
                "Please try again!",
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    public EventSignUpUI goToEventSignUpUI() {
        eventSignUpUI = new EventSignUpUI();
        this.dispose();
        return eventSignUpUI;
    }
}
