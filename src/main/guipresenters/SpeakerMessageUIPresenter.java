package main.guipresenters;

import main.controllers.ProgramController;
import main.gui_interface.ISpeakerMessageUI;

public class SpeakerMessageUIPresenter {
    private ProgramController programController;
    private ISpeakerMessageUI iSpeakerMessageUI;

    public SpeakerMessageUIPresenter(ISpeakerMessageUI iSpeakerMessageUI, ProgramController programController) {
        this.iSpeakerMessageUI = iSpeakerMessageUI;
        this.programController = programController;
    }
}
