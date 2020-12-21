package main.guipresenters;

import main.controllers.MessageController;
import main.controllers.ProgramController;
import main.gui_interface.IAttendeeMainUI;
import main.gui_interface.IAttendeeMessageUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ListSelectionListener;
import main.guilisteners.SendButtonListener;

import javax.swing.event.ListSelectionEvent;
import java.util.List;

/**
 * The presenter for <code>AttendeeMessageUI</code>
 *
 * @author Yi Tao Li
 */
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
        int userIndex = this.iAttendeeMessageUI.getUsersList().getSelectedIndex();
        String message = iAttendeeMessageUI.getMessage();
        if (userIndex != -1 && !message.equals("")) {
            List<String> userIds = this.messageController.
                    receiversForAttendeeAndOrganizer(this.programController.
                            getAuthController().fetchLoggedInUser());
            this.messageController.sendMessage(this.programController.
                    getAuthController().fetchLoggedInUser(),
                    userIds.get(userIndex), message);
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
