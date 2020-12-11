package main.guipresenters;

import main.controllers.ProgramController;
import main.gui_interface.IListOfUsersUI;
import main.gui_interface.IUserManagementUI;
import main.guilisteners.BackButtonListener;

@SuppressWarnings("FieldCanBeLocal")

public class ListOfUserUIPresenter implements BackButtonListener {

    private ProgramController programController;
    private IListOfUsersUI iListOfUsersUI;
    private IUserManagementUI iUserManagementUI;

    public ListOfUserUIPresenter(IListOfUsersUI listOfUsersUI,
                                 ProgramController programController) {
        this.iListOfUsersUI = listOfUsersUI;
        this.programController = programController;
        iListOfUsersUI.addBackButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        programController.saveForNext();
        iUserManagementUI = iListOfUsersUI.goToUserManagementUI();
        new UserManagementUIPresenter(iUserManagementUI, programController);
    }

}


