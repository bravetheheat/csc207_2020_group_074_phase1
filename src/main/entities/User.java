package main.entities;

import java.util.UUID;

/**
 * User represents a user of the program. User stores account details and the type of account (Attendee, Organizer,
 * Speaker).
 */

public class User {
    private String username;
    private String password;
    private String type;
    private String id;

    /**
     * Constructor for a User.
     *
     * @param email    Email of the user which will be used as the user's display name and log in username.
     * @param password Password of the user's account.
     * @param type     The type of account (Attendee, Organizer, Speaker, etc.)
     */
    public User(String email, String password, String type) {
        this.username = email;
        this.password = password;
        this.type = type;
        this.id = UUID.randomUUID().toString();
    }

    /**
     * Returns the username of the user.
     *
     * @return email of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of user.
     *
     * @param username for user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of the user.
     *
     * @return password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password password for user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the ID of the user.
     *
     * @return unique UUID of the user
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id ID of the user
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the type of account of user
     *
     * @return type of account
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets the type of the account.
     *
     * @param type account type
     */
    public void setRole(String type) {
        this.type = type;
    }
}
