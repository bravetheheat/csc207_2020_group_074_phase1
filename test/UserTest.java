import main.entities.Attendee;
import org.junit.*;
import main.entities.*;

import java.util.UUID;

public class UserTest {

    User attendee;
    User speaker;
    User organizer;

    @Before
    public void setup() {
        attendee = new Attendee("attendee@mail.utoronto.ca", "user123");
        speaker = new Speaker("speaker@mail.utoronto.ca", "speaker123");
        organizer = new Organizer("organizer@mail.utoronto.ca", "organizer123");
    }

    @Test
    public void testAttendeeGetters() {
        String email = attendee.getUsername();
        String password = attendee.getPassword();
        String role = attendee.getRole();
        Assert.assertEquals("attendee@mail.utoronto.ca", email);
        Assert.assertEquals("user123", password);
        Assert.assertEquals("Attendee", role);
    }

    @Test
    public void testSpeakerGetters() {
        String email = organizer.getUsername();
        String password = organizer.getPassword();
        String role = organizer.getRole();
        Assert.assertEquals("organizer@mail.utoronto.ca", email);
        Assert.assertEquals("organizer123", password);
        Assert.assertEquals("Organizer", role);
    }

    @Test
    public void testOrganizerGetters() {
        String email = speaker.getUsername();
        String password = speaker.getPassword();
        String role = speaker.getRole();
        Assert.assertEquals("speaker@mail.utoronto.ca", email);
        Assert.assertEquals("speaker123", password);
        Assert.assertEquals("Speaker", role);
    }

    @Test
    public void testIDUnique() {
        Assert.assertNotEquals(organizer.getId(), attendee.getId());
        Assert.assertNotEquals(organizer.getId(), speaker.getId());
        Assert.assertNotEquals(speaker.getId(), attendee.getId());
    }
}
