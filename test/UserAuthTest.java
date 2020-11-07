import main.entities.Attendee;
import main.entities.Organizer;
import main.entities.Speaker;
import main.entities.User;
import main.usecases.UsersManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.controllers.AuthController;

import java.util.UUID;

public class UserAuthTest {
    AuthController authController;
    UsersManager usersManager;

    @Before
    public void setUp() {
        this.usersManager = new UsersManager();
        this.authController = new AuthController(this.usersManager);
    }

    @Test
    public void shouldReturnNotLoggedInIfNotLoggedIn() {
        boolean isLoggedIn = this.authController.isLoggedIn();
        Assert.assertFalse(isLoggedIn);
    }

//    @Test
//    public void shouldReturnLoggedInIfLoggedIn() {
//
//    }

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

}
