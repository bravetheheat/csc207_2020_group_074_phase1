import main.usecases.ContactsManager;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;

public class ContactsManagerTest {
    UUID user1id;
    UUID user2id;
    UUID user3id;
    ContactsManager cm1;

    @Before
    public void setUp(){
        UUID user1id = UUID.randomUUID();
        UUID user2id = UUID.randomUUID();
        UUID user3id = UUID.randomUUID();
        ContactsManager cm1 = new ContactsManager();
    }

    @Test
    public void testAddNewUser(){
        cm1.addNewUser(user1id);
        cm1.addNewUser(user2id);
        cm1.addNewUser(user3id);
        Map<UUID, ArrayList<UUID>> contactlist = cm1.getUsersSaved();
        Assert.assertTrue(contactlist.containsKey(user1id));
        Assert.assertTrue(contactlist.containsKey(user2id));
        Assert.assertTrue(contactlist.containsKey(user3id));
    }

    @Test
    public void testAddUser(){
        cm1.addNewUser(user1id);
        cm1.addNewUser(user2id);
        cm1.addNewUser(user3id);
        cm1.addUser(user1id, user2id);
        cm1.addUser(user1id, user3id);
        ArrayList<UUID> user1list = cm1.getContactList(user1id);
        ArrayList<UUID> user2list = cm1.getContactList(user2id);
        ArrayList<UUID> user3list = cm1.getContactList(user3id);
        Assert.assertTrue(user1list.contains(user2id));
        Assert.assertTrue(user1list.contains(user3id));
        Assert.assertTrue(user2list.contains(user1id));
        Assert.assertFalse(user2list.contains(user3id));
        Assert.assertTrue(user3list.contains(user1id));
        Assert.assertFalse(user3list.contains(user2id));
    }

    @Test
    public void testRemoveUser(){
        cm1.addNewUser(user1id);
        cm1.addNewUser(user2id);
        cm1.addNewUser(user3id);
        cm1.addUser(user1id, user2id);
        cm1.addUser(user1id, user3id);
        cm1.removeUser(user2id, user1id);
        ArrayList<UUID> user1list = cm1.getContactList(user1id);
        ArrayList<UUID> user2list = cm1.getContactList(user2id);
        Assert.assertFalse(user1list.contains(user2id));
        Assert.assertFalse(user2list.contains(user1id));
    }

    @Test
    public void testGetContactList(){
        cm1.addNewUser(user1id);
        cm1.addNewUser(user2id);
        cm1.addNewUser(user3id);
        cm1.addUser(user1id, user2id);
        cm1.addUser(user1id, user3id);
        ArrayList<UUID> user1list = new ArrayList<UUID>();
        user1list.add(user2id);
        user1list.add(user3id);
        Assert.assertTrue(user1list == cm1.getContactList(user1id));
    }

    @Test
    public void testCheckPermission(){
        cm1.addNewUser(user1id);
        cm1.addNewUser(user2id);
        cm1.addNewUser(user3id);
        cm1.addUser(user1id, user2id);
        cm1.addUser(user1id, user3id);
        Assert.assertTrue(cm1.checkPermission(user1id, user2id));
        Assert.assertTrue(cm1.checkPermission(user1id, user3id));
        Assert.assertFalse(cm1.checkPermission(user2id, user3id));
    }
}
