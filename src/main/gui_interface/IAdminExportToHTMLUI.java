package main.gui_interface;

import main.gui.AdminMainUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ExportButtonListener;

import javax.swing.*;

public interface IAdminExportToHTMLUI extends IView {

    void addBackButtonListener(BackButtonListener backButtonListener);

    void addExportButtonListener(ExportButtonListener exportButtonListener);

    void notifyBackButtonListener();

    void notifyExportButtonListener();

    AdminMainUI goToAdminMainUI();

    JList getUsersList();

    void successfullyExported();

    void noEventsMessage();
}
