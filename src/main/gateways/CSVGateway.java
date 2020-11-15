package main.gateways;

import com.opencsv.bean.CsvToBeanBuilder;
import main.entities.Event;
import main.entities.User;
import main.gateways.beans.UserBean;
import main.usecases.UserFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVGateway implements Gateway {

    private final String userCSVPath = "src/store/Users.csv";

    public CSVGateway() {
    }


    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();

        try {
            // From documentation available at http://opencsv.sourceforge.net/

            List<UserBean> userBeans = new CsvToBeanBuilder(new BufferedReader(new FileReader(this.userCSVPath))).withType(UserBean.class).withSkipLines(1).build().parse();

            UserFactory userFactory = new UserFactory();
            for (UserBean userBean : userBeans) {
                User newUser = userFactory.getUser(userBean.getUsername(), userBean.getPassword(), userBean.getRole());
                users.add(newUser);
            }
        } catch (FileNotFoundException e) {
        } finally {
            return users;
        }


    }

    public void saveUsers(List<User> users) {
    }

    public List<Event> loadEvents() {
        return null;
    }

    public void saveEvents(List<Event> events) {
    }


}
