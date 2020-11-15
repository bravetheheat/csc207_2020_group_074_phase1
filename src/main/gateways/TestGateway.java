package main.gateways;


import main.entities.*;

import java.util.ArrayList;
import java.util.List;

public class TestGateway implements Gateway{

    List<User> users;
    List<Event> events;

    public TestGateway() {
        this.generateUsers();
        this.generateEvents();


    }

    /**
     * Generate test users
     */
    private void generateUsers() {
        this.users = new ArrayList<>();

        User user1 = new Organizer("organizer@gmail.com", "12345");
        User user2 = new Attendee("jimmy@gmail.com", "12345");
        User user3 = new Attendee("oliver@gmail.com", "12345");
        User user4 = new Speaker("yvette@gmail.com", "12345");

        this.users.add(user1);
        this.users.add(user2);
        this.users.add(user3);
        this.users.add(user4);
    }

    private void generateEvents() {
        this.events = new ArrayList<>();
    }

    public List<User> loadUsers() {
        return this.users;
    }


    public void saveUsers(List<User> users) {
        this.users = users;
    }

    public List<Event> loadEvents(){
        return this.events;
    }

    public void saveEvents(List<Event> events) {
        this.events = events;
    }




}
