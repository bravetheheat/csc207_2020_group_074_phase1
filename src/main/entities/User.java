package main.entities;

import java.util.UUID;

/**
 * User represents a user of the program.
 */

public abstract class User {
    protected String username;
    protected String password;
    protected String role;
    protected UUID id = UUID.randomUUID();

    public User (String email, String password, String role) {
        this.username = email;
        this.password = password;
        this.role = role;
    }
    public String getUsername() { return username; }

    public String getPassword() {
        return password;
    }

    public UUID getId() {
        return id;
    }

    public String getRole() {
        return this.role;
    }
}
