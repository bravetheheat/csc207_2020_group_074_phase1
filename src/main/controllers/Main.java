package main.controllers;

import main.gui.LandingUI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        ProgramController program = new ProgramController();
//        program.start();
        SwingUtilities.invokeLater(() -> new LandingUI(program));
    }
}
