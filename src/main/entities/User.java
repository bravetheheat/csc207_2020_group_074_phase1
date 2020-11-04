package main.entities;

import java.util.UUID;

public class User {
    private String username;
    private String password;
    private UUID id = UUID.randomUUID();

    public User (String email, String password) {
        this.username = email;
        this.password = password;
    }
    public String getUsername() { return username; }

    public String getPassword() {
        return password;
    }

    public UUID getId() {
        return id;
    }
}
