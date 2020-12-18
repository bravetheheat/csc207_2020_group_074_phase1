package main.gui_interface;

import main.gui.AdminMainUI;
import main.gui.OrganizerMainUI;
import main.guilisteners.*;

import javax.swing.*;

public interface IOrganizerMessageUI extends IView {


    void addBackButtonListener(BackButtonListener backButtonListener);

    void addSendButtonListener(SendButtonListener sendButtonListener);

    void addEveryoneButtonListener(EveryoneButtonListener everyoneButtonListener);

    void addAllAttendeesButtonListener(AllAttendeesButtonListener allAttendeesButtonListener);

    void addAllSpeakersButtonListener(AllSpeakersButtonListener allSpeakersButtonListener);

    void notifyBackButtonListener();

    void notifySendButtonListener();

    void notifyEveryoneButtonListener();

    void notifyAllAttendeesButtonListener();

    void notifyAllSpeakersButtonListener();

    String getMessage();

    OrganizerMainUI goToOrganizerMainUI();

    AdminMainUI goToAdminMainUI();

    JList getUsersList();

    void sendMessageSuccessful();

    void sendMessageError();
}
