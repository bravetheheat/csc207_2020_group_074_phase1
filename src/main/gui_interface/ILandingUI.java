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
    /**
     * Listens to actions performed on the login button and switch screen
     *
     * @param listener the object that implements <code>LoginButtonListener</code>
     */
    void addLoginButtonListener(LoginButtonListener listener);

    /**
     * Listens to actions performed on the register button and switch screen
     *
     * @param listener the object that implements <code>RegisterButtonListener</code>
     */
    void addRegisterButtonLister(RegisterButtonListener listener);

    /**
     * Call the presenter method to update accordingly
     */
    void notifyListenerOnLoginButtonClicked();

    /**
     * Call the presenter method to update accordingly
     */
    void notifyListenerOnRegisterButtonClicked();

    /**
     * Open up <code>LoginUI</code> frame
     * @return a <code>LoginUI</code>
     */
    ILoginUI goToLoginUI();

    /**
     * Open up <code>RegisterUI</code> frame
     * @return a <code>RegisterUI</code>
     */
    IRegisterUI goToRegisterUI();
}
