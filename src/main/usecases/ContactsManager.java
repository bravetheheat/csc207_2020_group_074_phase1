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

    /**
     * A user is added to the contact manager system with user id(UUID). The initial contact list of
     * the user is empty because the user does not have any contact except the user himself/herself
     * when the user account is initially created.
     * @param user that added to the contact manager
     */
    public ContactsManager(User user){
        ArrayList<User> contactList = new ArrayList<User>();
        contactList.add(user);
        contactListsofUsers.put(user.getId(), contactList);
    }

    /**
     * Add a friend to the user's contactList
     * @param user in the contactsListofUsers
     * @param friend that should be added to the contact list of the user
     */
    public void addUser(User user, User friend) {
        ArrayList<User> friends = contactListsofUsers.get(user.getId());
        friends.add(friend);
        contactListsofUsers.put(user.getId(), friends);
    }

    public ArrayList<User> getContactList(User user) {
        return contactListsofUsers.get(user.getId());
    }

    public void addUsers(User user, User friend) {

    }
}
