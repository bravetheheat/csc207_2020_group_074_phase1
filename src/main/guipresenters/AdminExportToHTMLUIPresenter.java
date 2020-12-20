package main.guipresenters;

import main.controllers.ProgramController;
import main.entities.Event;
import main.export.HTMLExport;
import main.gui_interface.IAdminExportToHTMLUI;
import main.gui_interface.IAdminMainUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ExportButtonListener;
import main.usecases.EventsManager;
import main.usecases.RoomManager;
import main.usecases.UsersManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminExportToHTMLUIPresenter implements BackButtonListener, ExportButtonListener {
    private ProgramController programController;
    private EventsManager eventsManager;
    private IAdminExportToHTMLUI iAdminExportToHTMLUI;

    public AdminExportToHTMLUIPresenter(IAdminExportToHTMLUI iAdminExportToHTMLUI, ProgramController programController) {
        this.programController = programController;
        this.eventsManager = programController.getEventsManager();
        this.iAdminExportToHTMLUI = iAdminExportToHTMLUI;
        this.iAdminExportToHTMLUI.addBackButtonListener(this);
        this.iAdminExportToHTMLUI.addExportButtonListener(this);
    }

    @Override
    public void onBackButtonClicked() {
        IAdminMainUI iAdminMainUI = this.iAdminExportToHTMLUI.goToAdminMainUI();
        new AdminMainUIPresenter(iAdminMainUI, this.programController);
    }

    @Override
    public void onExportButtonClicked() {
        String user = (String) this.iAdminExportToHTMLUI.getUsersList().getSelectedValue();
        if (user != null) {
            UsersManager usersManager = this.programController.getUsersManager();
            String username;
            int i = user.indexOf(",");
            username = user.substring(i + 2);
            String userID = usersManager.getIDFromUsername(username);
            ArrayList<String> eventIDs = this.eventsManager.getUserEvents(userID);
            Map<String, Event> schedule = this.eventsManager.getSchedule();
            List<Event> eventList = new ArrayList<>();
            for(String id : eventIDs){
                eventList.add(schedule.get(id));
            }
            if (!eventList.isEmpty()){
                HTMLExport export = new HTMLExport();
                RoomManager roomManager = this.programController.getRoomManager();
                export.exportEvents(roomManager, eventList);
                this.iAdminExportToHTMLUI.successfullyExported();
            }
            else {
                this.iAdminExportToHTMLUI.noEventsMessage();
            }
        }
    }
}
