package main.gui_interface;

import main.gui.*;
import main.guilisteners.BackButtonListener;
import main.guilisteners.SignUpButtonListener;
import main.guilisteners.ViewEventsButtonListener;

import java.util.ArrayList;

public interface IEventSignUpUI extends IView {
    void addSignUpButtonListener(SignUpButtonListener listener);
    void addViewEventsButtonListener(ViewEventsButtonListener listener);
    void addBackButtonListener(BackButtonListener listener);
    AttendeeMainUI goToAttendeeMainUI();
    OrganizerMainUI goToOrganizerMainUI();
    DisplayAllEventsUI goToDisplayAllEventsUI(ArrayList<String> listOfEvents);
    ViewUserEventsUI goToViewUserEventsUI(ArrayList<String> listOfUserEvents);
    AdminMainUI goToAdminMainUI();
}
