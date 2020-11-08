package main.usecases;

import main.entities.User;

import java.lang.reflect.Array;
import java.util.*;

/**
 * The ContactsManager holds a dictionary of lists of contacts of users.
 *
 * @author Zewen Ma
 * @version 2.0
 * @since 2020-11-02
 */

public class ContactsManager {

    private Map<UUID, ArrayList<UUID>> contactListsofUsers;

    /**
     * A user is added to the contact manager system with user id(UUID). The initial contact list of
     * the user is empty because the user does not have any contact except the user himself/herself
     * when the user account is initially created.
     * @param user that added to the contact manager
     */
    public ContactsManager(User user){
        ArrayList<UUID> contactList = new ArrayList<UUID>();
        contactList.add(user.getId());
        contactListsofUsers.put(user.getId(), contactList);
    }

    /**
     * Add a friend to the user's contactList and add user to friend's contactList as well
     * @param user in the contactsListofUsers
     * @param friend that should be added to the contact list of the user
     *
     * Precondition: A user can add friend to his/her contact list iff friend is a valid user.
     */
    public void addUser(User user, User friend) {
        ArrayList<UUID> friend1 = contactListsofUsers.get(user.getId());
        friend1.add(friend.getId());
        contactListsofUsers.put(user.getId(), friend1);
        ArrayList<UUID> friend2 = contactListsofUsers.get(friend.getId());
        friend2.add(user.getId());
        contactListsofUsers.put(friend.getId(), friend2);
    }

    /**
     * Remove a friend from the user's contactList and remove user from the friend's contactList as well.
     * @param user in the contactsListofUsers
     * @param friend that should be removed from the contact list of the user
     *
     * Precondition: A user can remove friend from his/her contact list iff friend is a valid user and the friend
     *               is in the contactList of user.
     */
    public void removeUser(User user, User friend){
        ArrayList<UUID> friend1 = contactListsofUsers.get(user.getId());
        friend1.remove(friend.getId());
        contactListsofUsers.put(user.getId(), friend1);
        ArrayList<UUID> friend2 = contactListsofUsers.get(friend.getId());
        friend2.remove(user.getId());
        contactListsofUsers.put(friend.getId(), friend2);
    }

    /**
     * Return a contact list of a user stored in contactListsofUsers
     * @param user in the contactsListofUsers
     */
    public ArrayList<UUID> getContactList(User user) {
        return contactListsofUsers.get(user.getId());
    }

    /**
     * Return True iff a user can message to friend.
     * A user can send a message to friend iff friend is in the contact list of the user and user is also in
     * the contact list of friend.
     * @param user in the contactsListofUsers
     */
    public boolean checkPermission(User user, User friend){
        ArrayList<UUID> friend1 = contactListsofUsers.get(user.getId());
        return friend1.contains(friend.getId());
    }
}
