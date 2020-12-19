package main.guipresenters;

import main.controllers.MessageController;
import main.controllers.ProgramController;
import main.gui_interface.IAttendeeMainUI;
import main.gui_interface.IAttendeeMessageUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ListSelectionListener;
import main.guilisteners.SendButtonListener;
import main.usecases.UsersManager;

import javax.swing.event.ListSelectionEvent;

public class AttendeeMessageUIPresenter implements BackButtonListener, SendButtonListener, ListSelectionListener {
    IAttendeeMessageUI iAttendeeMessageUI;
    ProgramController programController;
    MessageController messageController;

    public AttendeeMessageUIPresenter(IAttendeeMessageUI iAttendeeMessageUI, ProgramController programController) {
        this.iAttendeeMessageUI = iAttendeeMessageUI;
        this.programController = programController;
        this.messageController = programController.getMessageController();
        this.iAttendeeMessageUI.addBackButtonListener(this);
        this.iAttendeeMessageUI.addSendButtonListener(this);
        this.iAttendeeMessageUI.addListSelectionListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        IAttendeeMainUI iAttendeeMainUI = iAttendeeMessageUI.goToAttendeeMainUI();
        new AttendeeMainUIPresenter(iAttendeeMainUI, this.programController);
    }

    @Override
    public void onSendButtonClicked() {
        String user = (String) this.iAttendeeMessageUI.getUsersList().getSelectedValue();
        String message = iAttendeeMessageUI.getMessage();
        if (user != null && message.length() > 0) {
            UsersManager usersManager = this.programController.getUsersManager();
            String username;
            int i = user.indexOf(",");
            username = user.substring(i + 2);
            String userID = usersManager.getIDFromUsername(username);
            this.messageController.sendMessage(this.programController.getAuthController().fetchLoggedInUser(), userID, message);
            programController.saveForNext();
            iAttendeeMessageUI.sendMessageSuccessful();
        }
        else {
            iAttendeeMessageUI.sendMessageError();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }
}
