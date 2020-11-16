package main.gateways;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import main.entities.Event;
import main.entities.Room;
import main.entities.User;
import main.gateways.beans.RoomBean;
import main.gateways.beans.UserBean;
import main.gateways.converters.RoomConverter;
import main.usecases.UserFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVGateway implements Gateway {

    private final String userCSVPath = "src/store/Users.csv";
    private final String eventCSVPath = "src/store/Events.csv";
    private final String roomCSVPath = "src/store/Rooms.csv";

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
        List<UserBean> userBeans = new ArrayList<>();
        for (User user : users) {
            UserBean userBean = new UserBean();
            userBean.setUsername(user.getUsername());
            userBean.setPassword(user.getPassword());
            userBean.setRole(user.getRole());
        }
        try {
            // From documentation available at http://opencsv.sourceforge.net/
            FileWriter csvFileWriter = new FileWriter(this.userCSVPath);
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(csvFileWriter).build();
            beanToCsv.write(userBeans);

        } catch (IOException e) {
            System.out.println("IOException. Error writing file.");
        } catch (CsvDataTypeMismatchException e) {
            System.out.println("Error writing file. Check your data format.");
        } catch (CsvRequiredFieldEmptyException e) {
            System.out.println("Error writing file. Missing required field.");
        }
    }

    public List<Event> loadEvents() {
        try {
            // From documentation available at http://opencsv.sourceforge.net/

            List<Event> events = new CsvToBeanBuilder(new BufferedReader(new FileReader(this.eventCSVPath))).withType(Event.class).build().parse();
            return events;

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return null;
        }

    }

    public void saveEvents(List<Event> events) {
        try {
            // From documentation available at http://opencsv.sourceforge.net/
            FileWriter csvFileWriter = new FileWriter(this.eventCSVPath);
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(csvFileWriter).build();
            beanToCsv.write(events);
            csvFileWriter.close();

        } catch (IOException e) {
            System.out.println("IOException. Error writing file.");
        } catch (CsvDataTypeMismatchException e) {
            System.out.println("Error writing file. Check your data format.");
        } catch (CsvRequiredFieldEmptyException e) {
            System.out.println("Error writing file. Missing required field.");
        }
    }

    public List<Room> loadRooms() {
        try {
            // From documentation available at http://opencsv.sourceforge.net/
            RoomConverter converter = new RoomConverter();
            List<RoomBean> roomBeans = new CsvToBeanBuilder(new BufferedReader(new FileReader(this.roomCSVPath))).withType(RoomBean.class).build().parse();
            List<Room> rooms = converter.convertFromBeans(roomBeans);
            return rooms;

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return null;
        }

    }

    public void saveRooms(List<Room> rooms) {
        try {
            RoomConverter converter = new RoomConverter();
            List<RoomBean> roomBeans = converter.convertToBeans(rooms);
            // From documentation available at http://opencsv.sourceforge.net/
            FileWriter csvFileWriter = new FileWriter(this.roomCSVPath);

            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(csvFileWriter).build();
            beanToCsv.write(roomBeans);
            csvFileWriter.close();

        } catch (IOException e) {
            System.out.println("IOException. Error writing file.");
        } catch (CsvDataTypeMismatchException e) {
            System.out.println("Error writing file. Check your data format.");
        } catch (CsvRequiredFieldEmptyException e) {
            System.out.println("Error writing file. Missing required field.");
        }
    }


}
