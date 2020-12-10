package main.gui_interface;

import main.controllers.ProgramController;

/**
 * Abstract UI interface from which all UI interfaces extend
 *
 * @author Steven Yuan
 */
public interface IView {
    void dispose(); // needed when switching frames
    void goToNext(ProgramController programController);
}
