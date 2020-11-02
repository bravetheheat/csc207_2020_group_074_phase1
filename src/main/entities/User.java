package main.entities;

import java.util.UUID;

public abstract class User {
    private String username;
    private String password;
    private UUID id = UUID.randomUUID();

    public User (String email, String password) {
        this.username = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public UUID getId() {
        return id;
    }
}
