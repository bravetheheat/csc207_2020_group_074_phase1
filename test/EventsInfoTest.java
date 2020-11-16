import main.entities.Attendee;
import main.entities.Event;
import main.entities.User;
import main.usecases.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class EventsInfoTest {
    String[] eventsId;
    ArrayList<Event> events;
    EventsManager eventsManager;
    Map<String, Event> schedule;
    LocalDateTime time1;
    LocalDateTime time2;
    LocalDateTime time3;
    String room1;
    String room2;
    String speaker1;
    String speaker2;

    public EventBuilder setUpEvent(String title, LocalDateTime time, String roomID, String speakerID){
        EventBuilder eb = new EventBuilder();
        eb.setTitle(title);
        eb.setRoom(roomID);
        eb.setTime(time);
        eb.setSpeaker(speakerID);
        return eb;
    }

    @Before
    public void setUp(){
        this.eventsManager = new EventsManager();
        time1 = LocalDateTime.of(2020, 1,1, 12, 0 );
        time2 = LocalDateTime.of(2020, 1,1, 16, 0 );
        time3 = LocalDateTime.of(2020, 1,1, 17, 0 );
        room1 = UUID.randomUUID().toString();
        room2 = UUID.randomUUID().toString();
        speaker1 = UUID.randomUUID().toString();
        speaker2 = UUID.randomUUID().toString();
        EventBuilder e1 = setUpEvent("Event1", time1, room1, speaker1);
        eventsManager.scheduleEvent(e1);
        EventBuilder e2 = setUpEvent("Event2", time2, room1, speaker2);
        eventsManager.scheduleEvent(e2);
        EventBuilder e3 = setUpEvent("Event3", time3, room2, speaker2);
        eventsManager.scheduleEvent(e3);
        schedule = eventsManager.getSchedule();
        events = eventsManager.getEvents();
        eventsId = schedule.keySet().toArray(new String[events.size()]);
    }

    @Test
    public void testAddRemoveSpeaker(){
        //test one speaker can not add more
        EventInfoManager eim = new EventInfoManager(eventsId[0], schedule);
        Assert.assertFalse(eim.addSpeaker(speaker1));
        Assert.assertFalse(eim.addSpeaker(speaker2));
        //test one speaker can not be removed if not exist
        Assert.assertFalse(eim.addSpeaker(speaker2));
        //test one speaker can be removed
        Assert.assertTrue(eim.removeSpeaker(speaker1));
        //test add speaker when there is empty
        Assert.assertTrue(eim.addSpeaker(speaker2));
    }

    @Test
    public void testAddRemoveUser(){
        User u1 = new Attendee("user1@email.com", "password");
        User u2 = new Attendee("user2@email.com", "password");
        //test add one user to one event
        EventInfoManager eim1 = new EventInfoManager(eventsId[0], schedule);
        Assert.assertTrue(eim1.addUser(u1.getId()));
        //test add multiple users to one event
        Assert.assertTrue(eim1.addUser(u2.getId()));
        //test add multiple users to multiple events
        EventInfoManager eim2 = new EventInfoManager(eventsId[1], schedule);
        Assert.assertTrue(eim2.addUser(u1.getId()));
        Assert.assertTrue(eim2.addUser(u2.getId()));
        //test remove users from empty event
        EventInfoManager eim3 = new EventInfoManager(eventsId[2], schedule);
        Assert.assertFalse(eim3.removeUser(u1.getId()));
        //test remove existing users from multiple events
        Assert.assertTrue(eim2.removeUser(u1.getId()));
        Assert.assertTrue(eim2.removeUser(u2.getId()));
        Assert.assertTrue(eim1.removeUser(u2.getId()));
        //test remove non-existence user
        Assert.assertFalse(eim1.removeUser(u2.getId()));
        //test remove last user
        Assert.assertTrue(eim1.removeUser(u1.getId()));
    }

    @Test
    public void testUpdateEventInfo(){
        //test changing time no conflict
        EventInfoManager eim = new EventInfoManager(eventsId[1], schedule);
        Assert.assertTrue(eim.updateEventInfo(time1,room2));
        //test changing time and time conflict with event1
        Assert.assertFalse(eim.updateEventInfo(time1, room1));
        //test changing room and speaker conflict with event3
        Assert.assertFalse(eim.updateEventInfo(time3, room1));
    }

}
