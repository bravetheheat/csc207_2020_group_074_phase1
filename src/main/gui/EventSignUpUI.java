package main.gui;

import main.gui_interface.IEventSignUpUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.SignUpButtonListener;
import main.guilisteners.ViewEventsButtonListener;

import javax.swing.*;
import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")

public class EventSignUpUI extends JFrame implements IEventSignUpUI {
    private JButton backButton;
    private JPanel panel1;
    private JButton signUpButton;
    private JButton viewEventsButton;
    private AttendeeMainUI attendeeMainUI;
    private OrganizerMainUI organizerMainUI;
    private SignUpButtonListener signUpButtonListener;
    private ViewEventsButtonListener viewEventsButtonListener;
    private BackButtonListener backButtonListener;
    private DisplayAllEventsUI displayAllEventsUI;
    private ViewUserEventsUI viewUserEventsUI;

    public EventSignUpUI() {
        this.setTitle("Sign Up or Cancel Events");
        this.setSize(600, 500);
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        signUpButton.addActionListener(e -> notifyListenerOnSignUpButtonClicked());

        viewEventsButton.addActionListener(e -> notifyListenerOnViewEventsButtonClicked());

        backButton.addActionListener(e -> notifyListenerBackButtonClicked());
    }

    public void notifyListenerBackButtonClicked() {
        backButtonListener.onBackButtonClicked();
    }

    public void notifyListenerOnSignUpButtonClicked() {
        signUpButtonListener.onSignUpButtonClicked();
    }

    public void notifyListenerOnViewEventsButtonClicked() {
        viewEventsButtonListener.onViewEventsButtonClicked();
    }

    @Override
    public void addSignUpButtonListener(SignUpButtonListener listener) {
        signUpButtonListener = listener;
    }

    @Override
    public void addViewEventsButtonListener(ViewEventsButtonListener listener) {
        viewEventsButtonListener = listener;
    }

    @Override
    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    @Override
    public AttendeeMainUI goToAttendeeMainUI() {
        attendeeMainUI = new AttendeeMainUI();
        this.dispose();
        return attendeeMainUI;
    }

    @Override
    public OrganizerMainUI goToOrganizerMainUI() {
        organizerMainUI = new OrganizerMainUI();
        this.dispose();
        return organizerMainUI;
    }

    @Override
    public DisplayAllEventsUI goToDisplayAllEventsUI(ArrayList<String> listOfEvents) {
        displayAllEventsUI = new DisplayAllEventsUI(listOfEvents);
        this.dispose();
        return displayAllEventsUI;
    }

    @Override
    public ViewUserEventsUI goToViewUserEventsUI(ArrayList<String> listOfUserEvents) {
        viewUserEventsUI = new ViewUserEventsUI(listOfUserEvents);
        this.dispose();
        return viewUserEventsUI;
    }
}
