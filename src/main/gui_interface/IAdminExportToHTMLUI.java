package main.gui_interface;

import main.gui.AdminMainUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ExportButtonListener;

import javax.swing.*;

/**
 * The <code>AdminExportToHTMLUIPresenter</code> will depend on this interface which is
 * implemented by <code>AdminExportToHTMLUI</code>
 *
 * @author Yi Tao Li
 */
public interface IAdminExportToHTMLUI extends IView {

    /**
     * Listens to actions performed on the back button and returns to main screen
     *
     * @param backButtonListener the object that implements <code>BackButtonListener</code>
     */
    void addBackButtonListener(BackButtonListener backButtonListener);

    /**
     * Listens to actions performed on the export button and exports the user's event schedule as an HTML file
     *
     * @param exportButtonListener the object that implements <code>ExportButtonListener</code>
     */
    void addExportButtonListener(ExportButtonListener exportButtonListener);

    /**
     * Call the presenter method to update accordingly
     */
    void notifyBackButtonListener();

    /**
     * Call the presenter method to update accordingly
     */
    void notifyExportButtonListener();

    /**
     * Open up <code>AdminUI</code> frame
     * @return a <code>AdminUI</code>
     */
    AdminMainUI goToAdminMainUI();

    /**
     * Retrieves list of users
     * @return list of users
     */
    JList getUsersList();

    /**
     * Display "successfully exported" notification
     */
    void successfullyExported();

    /**
     * Display "no events" notification
     */
    void noEventsMessage();
}
