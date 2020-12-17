package main.gui_interface;

import main.gui.SpeakerMainUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.BroadcastButtonListener;
import main.guilisteners.SendButtonListener;

import javax.swing.*;
import java.util.ArrayList;

public interface ISpeakerMessageUI extends IView {

    void addBackButtonListener(BackButtonListener backButtonListener);

    void addSendButtonListener(SendButtonListener sendButtonListener);

    void addBroadcastButtonListener(BroadcastButtonListener broadcastButtonListener);

    void notifyBroadcastButtonListener();

    void notifyBackButtonListener();

    void notifySendButtonListener();
;
    String getMessage();

    SpeakerMainUI goToSpeakerMainUI();

    JList getUsersList();

    JList getEventsList();

    void sendMessageSuccessful();

    void sendMessageError();

}
