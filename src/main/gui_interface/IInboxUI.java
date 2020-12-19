package main.gui_interface;

import main.gui.AdminMainUI;
import main.gui.AttendeeMainUI;
import main.gui.OrganizerMainUI;
import main.gui.SpeakerMainUI;
import main.guilisteners.BackButtonListener;

import java.util.ArrayList;

public interface IInboxUI extends IView {
    void notifyListenerOnBackButtonClicked();

    void addBackButtonListener(BackButtonListener backButtonListener);

    AttendeeMainUI goToAttendeeMainUI();

    SpeakerMainUI goToSpeakerMainUI();

    OrganizerMainUI goToOrganizerMainUI();

    AdminMainUI goToAdminMainUI();
}
