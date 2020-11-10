package main.usecases;

import java.util.*;

/**
 * The ContactsManager holds a dictionary of lists of contacts of users.
 *
 * @author Zewen Ma
 * @version 3.0
 * @since 2020-11-02
 */

public class ContactsManager {

    private Map<UUID, ArrayList<UUID>> contactListsofUsers;

    /**
     * A user is added to the contact manager system with user id(UUID). The initial contact list of
     * the user is empty because the user does not have any contact except the user himself/herself
     * when the user account is initially created.
     * @param userid that added to the contact manager
     */
    public void addNewUser(UUID userid){
        ArrayList<UUID> contactList = new ArrayList<UUID>();
        contactList.add(userid);
        contactListsofUsers.put(userid, contactList);
    }

    /**
     * Add a friend to the user's contactList and add user to friend's contactList as well
     * @param userid in the contactsListofUsers
     * @param friendid that should be added to the contact list of the user
     *
     * Precondition: A user can add friend to his/her contact list iff friend is a valid user.
     */
    public void addUser(UUID userid, UUID friendid) {
        ArrayList<UUID> friend1 = contactListsofUsers.get(userid);
        friend1.add(friendid);
        contactListsofUsers.put(userid, friend1);
        ArrayList<UUID> friend2 = contactListsofUsers.get(friendid);
        friend2.add(userid);
        contactListsofUsers.put(friendid, friend2);
    }

    /**
     * Remove a friend from the user's contactList and remove user from the friend's contactList as well.
     * @param userid in the contactsListofUsers
     * @param friendid that should be removed from the contact list of the user
     *
     * Precondition: A user can remove friend from his/her contact list iff friend is a valid user and the friend
     *               is in the contactList of user.
     */
    public void removeUser(UUID userid, UUID friendid){
        ArrayList<UUID> friend1 = contactListsofUsers.get(userid);
        friend1.remove(friendid);
        contactListsofUsers.put(userid, friend1);
        ArrayList<UUID> friend2 = contactListsofUsers.get(friendid);
        friend2.remove(userid);
        contactListsofUsers.put(friendid, friend2);
    }

    /**
     * Return a contact list of a user stored in contactListsofUsers
     * @param userid in the contactsListofUsers
     */
    public ArrayList<UUID> getContactList(UUID userid) {
        return contactListsofUsers.get(userid);
    }

    /**
     * Return True iff a user can message to friend.
     * A user can send a message to friend iff friend is in the contact list of the user and user is also in
     * the contact list of friend.
     * @param userid in the contactsListofUsers
     * @param friendid that needs to be checked permission
     */
    public boolean checkPermission(UUID userid, UUID friendid){
        ArrayList<UUID> friend1 = contactListsofUsers.get(userid);
        return friend1.contains(friendid);
    }

    /**
     * Return a Map of users and their contact lists saved in ContactsManager
     * @return a Map of users and their contact lists saved in ContactsManager
     */
    public Map<UUID, ArrayList<UUID>> getUsersSaved(){
        return this.contactListsofUsers;
    }
}
