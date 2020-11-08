package main.entities;

import java.util.ArrayList;
import java.util.List;

public class Organizer extends User {
    private List<String> eventNames;

    public Organizer (String email, String password) {
        super(email, password, "Organizer");
        this.eventNames = new ArrayList<>();
    }
}
