package main.guipresenters;

import main.controllers.ProgramController;
import main.gui.GatewayUI;
import main.gui_interface.IGatewayUI;

public class GatewayUIPresenter {
    GatewayUI gatewayUI;
    ProgramController programController;
    public GatewayUIPresenter(IGatewayUI iGatewayUI, ProgramController programController) {
        this.programController = programController;
        this.gatewayUI = new GatewayUI();
    }
}
