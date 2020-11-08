package main.controllers;

import main.usecases.UsersManager;

public class ProgramController {
    UsersManager usersManager;
    AuthController authController;

    public ProgramController() {
        this.usersManager = new UsersManager();
        this.authController = new AuthController(usersManager);
    }
}
