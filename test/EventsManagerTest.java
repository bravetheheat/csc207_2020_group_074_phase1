import main.entities.Attendee;
import main.entities.Event;
import main.entities.User;
import main.usecases.EventsManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.CoreMatchers.containsString;

public class EventsManagerTest {
    EventsManager eventsManager;
    LocalDateTime time1;
    LocalDateTime time2;
    UUID room1;
    UUID room2;
    UUID speaker1;
    UUID speaker2;

    @Before
    public void setUp(){
        this.eventsManager = new EventsManager();
        time1 = LocalDateTime.of(2020, 1,1, 12, 0 );
        time2 = LocalDateTime.of(2020, 1,1, 16, 0 );
        room1 = UUID.randomUUID();
        room2 = UUID.randomUUID();
        speaker1 = UUID.randomUUID();
        speaker2 = UUID.randomUUID();
    }

    @Test
    public void testScheduleEvent(){
        //test initial
        Event e1 = new Event("Event1", time1, room1, speaker1);
        Assert.assertTrue(eventsManager.scheduleEvent(e1));
        //test same room same speaker
        Event e2 = new Event("Event2", time2, room1, speaker1);
        Assert.assertTrue(eventsManager.scheduleEvent(e2));
        //test speaker conflict
        Event e3 = new Event("Event3", time1, room2, speaker1);
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> eventsManager.scheduleEvent(e3));
        String expected1 = "Time conflict for speaker " + speaker1 + " with Event #" + e1.getTitle();
        Assert.assertEquals(expected1, exception1.getMessage());
        //test room conflict
        Event e4 = new Event("Event4", time1, room1, speaker2);
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> eventsManager.scheduleEvent(e4));
        String expected2 = "Time conflict for room " + room1 + " with Event #" + e1.getTitle();
        Assert.assertEquals(expected2, exception2.getMessage());
    }

    @Test
    public void testRemoveEvent(){
        //test empty
        Event e1 = new Event("Event1", time1, room1, speaker1);
        Assert.assertFalse(eventsManager.removeEvent(e1.getId()));
        //test not in schedule
        eventsManager.scheduleEvent(e1);
        Event e2 = new Event("Event2", time2, room1, speaker1);
        Assert.assertFalse(eventsManager.removeEvent(e2.getId()));
        //test success removes
        eventsManager.scheduleEvent(e2);
        Assert.assertTrue(eventsManager.removeEvent(e1.getId()));
        Assert.assertTrue(eventsManager.removeEvent(e2.getId()));
    }

    @Test
    public void testUserEvents(){
        Event e1 = new Event("Event1", time1, room1, speaker1);
        eventsManager.scheduleEvent(e1);
        Event e2 = new Event("Event2", time2, room1, speaker1);
        eventsManager.scheduleEvent(e2);
        User u1 = new Attendee("user1@email.com", "password");
        e1.addAttendees(u1.getId());
        //test single
        Assert.assertEquals(1, eventsManager.getUserEvents(u1.getId()).size());
        Assert.assertTrue(eventsManager.getEvents().contains(e1));
        //test multiple
        e2.addAttendees(u1.getId());
        Assert.assertEquals(2, eventsManager.getUserEvents(u1.getId()).size());
        Assert.assertTrue(eventsManager.getEvents().contains(e1));
        Assert.assertTrue(eventsManager.getEvents().contains(e2));
    }

    @Test
    public void testGetEvents(){
        Event e1 = new Event("Event1", time1, room1, speaker1);
        eventsManager.scheduleEvent(e1);
        Event e2 = new Event("Event2", time2, room1, speaker1);
        //test single
        Assert.assertEquals(1, eventsManager.getEvents().size());
        Assert.assertTrue(eventsManager.getEvents().contains(e1));
        //test multiple
        eventsManager.scheduleEvent(e2);
        Assert.assertEquals(2, eventsManager.getEvents().size());
        Assert.assertTrue(eventsManager.getEvents().contains(e1));
        Assert.assertTrue(eventsManager.getEvents().contains(e2));
    }

    @Test
    public void testToString(){
        Event e1 = new Event("Event1", time1, room1, speaker1);
        eventsManager.scheduleEvent(e1);
        Event e2 = new Event("Event2", time2, room1, speaker1);
        eventsManager.scheduleEvent(e2);
        Event e3 = new Event("Event3", time2, room2, speaker2);
        eventsManager.scheduleEvent(e3);
        Assert.assertThat(eventsManager.toString(), containsString(e1.toString()));
        Assert.assertThat(eventsManager.toString(), containsString(e2.toString()));
        Assert.assertThat(eventsManager.toString(), containsString(e3.toString()));
    }

}
