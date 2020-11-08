package main.entities;

public class Attendee extends User{

    public Attendee (String email, String password) {
        super(email, password, "Attendee");
    }
}
