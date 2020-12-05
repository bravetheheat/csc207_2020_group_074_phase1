package main.gateways.converters;

import main.entities.Event;
import main.entities.User;
import main.gateways.beans.EventBean;
import main.gateways.beans.UserBean;
import main.usecases.UserFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of Converter that serializes and deserializes Event
 *
 * @author David Zhao
 */
public class UserConverter implements Converter<UserBean, User>{


    public List<User> convertFromBeans(List<UserBean> userBeans) {
        List<User> users = new ArrayList<>();

        UserFactory userFactory = new UserFactory();
        for (UserBean userBean: userBeans) {
            User user = userFactory.getUser(userBean.getUsername(), userBean.getPassword(), userBean.getRole());
            user.setId(userBean.getId());
            users.add(user);

        }
        return users;

    }

    public List<UserBean> convertToBeans(List<User> users) {
        List<UserBean> userBeanList = new ArrayList<>();

        for (User user: users) {
            UserBean userBean = new UserBean();
            userBean.setId(user.getId());
            userBean.setUsername(user.getUsername());
            userBean.setPassword(user.getPassword());
            userBean.setRole(user.getType());
            userBeanList.add(userBean);
        }

        return userBeanList;

    }
}
