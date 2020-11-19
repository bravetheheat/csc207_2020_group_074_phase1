package main.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * A high-privilege user that can create new users, modify events, and handle application administration.
 */
public class Organizer extends User {

    public Organizer (String email, String password) {
        super(email, password, "Organizer");
    }
}
