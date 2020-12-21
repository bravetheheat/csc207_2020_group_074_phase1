package main.controllers;

import main.gui.LandingUI;
import main.guipresenters.LandingUIPresenter;

import javax.swing.*;

public class ProgramX {

    public ProgramX() {
        // model-view-presenter
        LandingUI landingUI = new LandingUI(); // view
        ProgramController program = new ProgramController(); // model
        new LandingUIPresenter(landingUI, program); // presenter
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProgramX::new);
    }
}
