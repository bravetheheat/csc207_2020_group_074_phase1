package main.controllers;

import main.gui.*;
import main.guipresenters.LandingUIPresenter;

import javax.swing.*;

public class Main {

    public Main() {
        // model-view-presenter
        LandingUI landingUI = new LandingUI(); // view
        ProgramController program = new ProgramController(); // model
        new LandingUIPresenter(landingUI, program); // presenter
    }

    public static void main(String[] args) {
//        program.start();
        SwingUtilities.invokeLater(Main::new);
    }
}
