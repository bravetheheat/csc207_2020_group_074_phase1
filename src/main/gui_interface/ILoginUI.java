package main.gui_interface;

import main.gui.LandingUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.LoginUIListener;

public interface ILoginUI extends IView {
    void addLoginUIListener(LoginUIListener listener);

    void addBackButtonListener(BackButtonListener listener);

    void notifyListenerOnLoginButtonClicked();

    void notifyListenerOnBackButtonClicked();

    String getUserName();

    String getPwd();

    LandingUI goToLandingUI();
}
