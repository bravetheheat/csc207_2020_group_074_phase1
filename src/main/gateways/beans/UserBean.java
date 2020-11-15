package main.gateways.beans;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.io.Serializable;

public class UserBean implements Serializable {
    @CsvBindByPosition(position=0)
    private String username;

    @CsvBindByPosition(position=1)
    private String password;

    @CsvBindByPosition(position=2)
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
}
