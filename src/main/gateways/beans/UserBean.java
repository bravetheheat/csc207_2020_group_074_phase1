package main.gateways.beans;
import com.opencsv.bean.CsvBindByPosition;

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

    public UserBean(){};

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return this.role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
