package main.gui;

import main.controllers.ProgramController;
import main.gui_interface.IMessageSentUI;
import main.guilisteners.BackButtonListener;

import javax.swing.*;

public class MessageSentUI extends NotificationUI implements IMessageSentUI {

    private JLabel jLabel;
    private JButton okButton;
    private BackButtonListener backButtonListener;
    private ProgramController programController;

    public MessageSentUI(ProgramController programController) {
        super();
        this.programController = programController;
        jLabel = new JLabel();
        addMessage(jLabel, "Message sent!");
        setVisible(true);
    }

    @Override
    public void goToAttendeeMessageUI() {
        this.dispose();
    }

    @Override
    public void goToSpeakerMessageUI() {
        this.dispose();
    }

}
