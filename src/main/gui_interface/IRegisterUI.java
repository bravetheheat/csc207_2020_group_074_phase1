package main.gui_interface;

import main.gui.RegisterMessageErrorUI;
import main.gui.RegisterMessageSuccessfulUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.RegisterUIListener;

/**
 * Interface for <code>RegisterUI</code>
 *
 * @author Steven Yuan
 */
public interface IRegisterUI extends IView {

    void addRegisterUIListener(RegisterUIListener listener);

    void addBackButtonListener(BackButtonListener listener);

    void notifyListenerOnConfirmButtonClicked();

    void notifyListenerOnBackButtonClicked();

    String getUserType();

    String getUserName();

    String getPwd();

    ILandingUI goToLandingUI();

    RegisterMessageSuccessfulUI goToSuccessfulUI();

    RegisterMessageErrorUI goToErrorUI();
}
