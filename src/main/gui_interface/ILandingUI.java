package main.gui_interface;

import main.guilisteners.LoginButtonListener;
import main.guilisteners.RegisterButtonListener;

/**
 * The <code>LandingUIPresenter</code> will depend on this interface which is
 * implemented by <code>LandingUI</code>
 *
 * @author Steven Yuan
 */
public interface ILandingUI extends IView {
    void addLoginButtonListener(LoginButtonListener listener);

    void addRegisterButtonLister(RegisterButtonListener listener);

    void notifyListenerOnLoginButtonClicked();

    void notifyListenerOnRegisterButtonClicked();

    ILoginUI goToLoginUI();

    IRegisterUI goToRegisterUI();
}
