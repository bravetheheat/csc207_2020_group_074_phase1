package main.gateways.beans;

import java.io.Serializable;

/**
 * UserBean is used to serialize and deserialize users
 *
 * @author David Zhao
 */
public class UserBean implements Serializable {
    private String id;
    private String username;
    private String password;
    private String role;

    public UserBean() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
