import main.controllers.AuthController;
import main.controllers.ProgramController;
import main.entities.Attendee;
import main.entities.Organizer;
import main.entities.Speaker;
import main.entities.User;
import main.usecases.UsersManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class UserAuthTest {
    AuthController authController;
    UsersManager usersManager;
    ProgramController programController;

    @Before
    public void setUp() {
        this.usersManager = new UsersManager();
        this.authController = new AuthController(this.programController);
    }

    @Test
    public void shouldReturnNotLoggedInIfNotLoggedIn() {
        boolean isLoggedIn = this.authController.isLoggedIn();
        Assert.assertFalse(isLoggedIn);
    }

    @Test
    public void testAddAttendee() {
        String username = "james@gmail.com";
        String password = "ShakenNotStirred";
        String userType = "Attendee";

        boolean registerSuccess = this.authController.registerUser(username, password, userType);
        boolean loginSuccess = this.authController.login(username, password);

        UUID loggedInUserId = this.authController.fetchLoggedInUser();
        User createdUser = this.usersManager.fetchUser(loggedInUserId);

        Assert.assertTrue(registerSuccess);
        Assert.assertTrue(loginSuccess);
        Assert.assertEquals(createdUser.getId(), loggedInUserId);
        Assert.assertEquals(createdUser.getUsername(), username);
        Assert.assertEquals(createdUser.getPassword(), password);
        Assert.assertTrue(createdUser instanceof Attendee);

    }

    @Test
    public void testAddSpeaker() {
        String username = "james@gmail.com";
        String password = "ShakenNotStirred";
        String userType = "Speaker";

        boolean registerSuccess = this.authController.registerUser(username, password, userType);
        boolean loginSuccess = this.authController.login(username, password);

        UUID loggedInUserId = this.authController.fetchLoggedInUser();
        User createdUser = this.usersManager.fetchUser(loggedInUserId);

        Assert.assertTrue(registerSuccess);
        Assert.assertTrue(loginSuccess);
        Assert.assertEquals(createdUser.getId(), loggedInUserId);
        Assert.assertEquals(createdUser.getUsername(), username);
        Assert.assertEquals(createdUser.getPassword(), password);
        Assert.assertTrue(createdUser instanceof Speaker);

    }

    @Test
    public void testAddOrganizer() {
        String username = "james@gmail.com";
        String password = "ShakenNotStirred";
        String userType = "Organizer";

        boolean registerSuccess = this.authController.registerUser(username, password, userType);
        boolean loginSuccess = this.authController.login(username, password);

        UUID loggedInUserId = this.authController.fetchLoggedInUser();
        User createdUser = this.usersManager.fetchUser(loggedInUserId);

        Assert.assertTrue(registerSuccess);
        Assert.assertTrue(loginSuccess);
        Assert.assertEquals(createdUser.getId(), loggedInUserId);
        Assert.assertEquals(createdUser.getUsername(), username);
        Assert.assertEquals(createdUser.getPassword(), password);
        Assert.assertTrue(createdUser instanceof Organizer);
    }

    @Test
    public void testLoginAndLogout() {
        String username = "james@gmail.com";
        String password = "ShakenNotStirred";

        // User newUser = new User(username, password);
        User newUser = new Attendee(username, password);
        this.usersManager.addUser(newUser);

        boolean loginSuccess = this.authController.login(username, password);
        boolean isLoggedIn = this.authController.isLoggedIn();
        UUID loggedInUserId = this.authController.fetchLoggedInUser();
        this.authController.logout();
        UUID loggedOutUserId = this.authController.fetchLoggedInUser();

        Assert.assertTrue(loginSuccess);
        Assert.assertTrue(isLoggedIn);
        Assert.assertEquals(newUser.getId(), loggedInUserId);
        Assert.assertNull(loggedOutUserId);
    }


    @Test
    public void testAddAndDeleteUser() {
        String username = "james@gmail.com";
        String password = "ShakenNotStirred";

        // User newUser = new User(username, password);
        User newUser = new Attendee(username, password);
        UUID newUserId = newUser.getId();
        this.usersManager.addUser(newUser);
        User addedUser = this.usersManager.fetchUser(newUserId);
        this.usersManager.removeUser(newUserId);
        User removedUser = this.usersManager.fetchUser(newUserId);

        Assert.assertEquals(addedUser, newUser);
        Assert.assertNull(removedUser);
    }

}
