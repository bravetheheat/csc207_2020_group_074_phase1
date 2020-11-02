package main.usecases;

import main.entities.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.UUID;

/**
 * The ContactsManager holds a dictionary of lists of contacts of users.
 *
 * @author Zewen Ma
 * @version 1.0
 * @since 2020-11-02
 */

public class ContactsManager {

    private Dictionary<UUID, ArrayList<main.entities.User>> contactListsofUsers;

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

    /**
     * Remove a friend from the user's contactList
     * @param user in the contactsListofUsers
     * @param friend that should be removed from the contact list of the user
     */
    public void removeUser(User user, User friend){
        ArrayList<User> friends = contactListsofUsers.get(user.getId());
        friends.remove(friend);
        contactListsofUsers.put(user.getId(), friends);
    }

    /**
     * Return a contact list of a user stored in contactListsofUsers
     * @param user in the contactsListofUsers
     */
    public ArrayList<User> getContactList(User user) {
        return contactListsofUsers.get(user.getId());
    }

    /**
     * Return True iff a user can message to friend.
     * A user can send a message to friend iff friend is in the contact list of the user.
     * @param user in the contactsListofUsers
     */
    public boolean checkPermission(User user, User friend){
        ArrayList<User> friends = contactListsofUsers.get(user.getId());
        return friends.contains(friend);
    }
}
