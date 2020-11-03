package main.usecases;

import main.entities.User;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.UUID;

/**
 * The ContactsManager holds a dictionary of lists of contacts of users.
 *
 * @author Zewen Ma
 * @version 2.0
 * @since 2020-11-02
 */

public class ContactsManager {

    private Dictionary<UUID, ArrayList<User>> contactListsofUsers;
    private ArrayList<User> contactList;

    public ContactsManager(User user){
        this.contactList = new ArrayList<User>();
    }

    public ArrayList<User> getContactList(User user) {
        return contactListsofUsers.get(user.getId());
    }

    public void addUsers(User user, User friend) {

    }
}
