import main.entities.Event;
import main.usecases.EventsManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

public class EventsManagerTest {
    EventsManager eventsManager;
    LocalDateTime time1;
    LocalDateTime time2;
    LocalDateTime time3;
    LocalDateTime time4;
    int duration1;
    int duration2;
    int duration3;
    int duration4;
    Event event1;
    Event event2;
    Event event3;

    @Before
    public void setUp() {
        this.eventsManager = new EventsManager();
        time1 = LocalDateTime.of(2020, 10, 10, 10, 00);
        time2 = LocalDateTime.of(2020, 10, 10, 9, 00);
        time3 = LocalDateTime.of(2020, 1, 1, 15,0);
        time4 = LocalDateTime.of(2020,1,1,17,10);
        duration1 = 120;
        duration2 = 135;
        duration3 = 50;
        duration4 = 80;
        event1 = new Event("hello", time1, "0", 45, 2);
        event2 = new Event("hi", time2, "0", 40, 40);
        event3 = new Event("bye", time3, "0", 60, 40);
    }

    @Test
    public void testGetEndTime(){
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("hour", 10);
        map.put("minute", 45);
        Assert.assertTrue(eventsManager.getEndTime(time1, 45).equals(map));
    }

    @Test
    public void testGetEndTime2(){
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("hour", 9);
        map.put("minute", 50);
        Assert.assertTrue(eventsManager.getEndTime(time2, 50).equals(map));
    }

    @Test
    public void testGetEndTime3(){
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("hour", 18);
        map.put("minute", 45);
        Assert.assertTrue(eventsManager.getEndTime(time1, duration2).equals(map));
    }

    @Test
    public void testCheckConflictTime(){
        Assert.assertFalse(eventsManager.checkConflictTime(event1, time2, 50));
    }

    @Test
    public void testCheckConflictTime1(){
        Assert.assertTrue(eventsManager.checkConflictTime(event2, time3, 50));
    }
}


//
//    public EventBuilder setUpEvent(String title, LocalDateTime time, String roomID, String speakerID){
//        EventBuilder eb = new EventBuilder();
//        eb.setTitle(title);
//        eb.setRoom(roomID);
//        eb.setTime(time);
//        eb.setSpeaker(speakerID);
//        return eb;
//    }
//
//    @Test
//    public void testScheduleEvent(){
//        //test initial
//        EventBuilder e1 = setUpEvent("Event1", time1, room1, speaker1);
//        Assert.assertTrue(eventsManager.scheduleEvent(e1));
//        //test same room same speaker
//        EventBuilder e2 = setUpEvent("Event2", time2, room1, speaker1);
//        Assert.assertTrue(eventsManager.scheduleEvent(e2));
//        //test speaker conflict
//        EventBuilder e3 = setUpEvent("Event3", time1, room2, speaker1);
//        Assert.assertFalse(eventsManager.scheduleEvent(e3));
//        //test room conflict
//        EventBuilder e4 = setUpEvent("Event4", time1, room1, speaker2);
//        Assert.assertFalse(eventsManager.scheduleEvent(e4));
//    }
//
//    @Test
//    public void testRemoveEvent(){
//        //test empty
//        EventBuilder e1 = setUpEvent("Event1", time1, room1, speaker1);
//        Assert.assertFalse(eventsManager.removeEvent(e1.toEvent().getId()));
//        //test not in schedule
//        eventsManager.scheduleEvent(e1);
//        EventBuilder e2 = setUpEvent("Event2", time2, room1, speaker1);
//        Assert.assertFalse(eventsManager.removeEvent(e2.toEvent().getId()));
//        //test success removes
//        eventsManager.scheduleEvent(e2);
//        String e1id =  eventsManager.getEvents().get(0).getId();
//        String e2id =  eventsManager.getEvents().get(1).getId();
//        Assert.assertTrue(eventsManager.removeEvent(e1id));
//        Assert.assertTrue(eventsManager.removeEvent(e2id));
//    }
//
//    @Test
//    public void testUserEvents(){
//        EventBuilder e1 = setUpEvent("Event1", time1, room1, speaker1);
//        eventsManager.scheduleEvent(e1);
//        EventBuilder e2 = setUpEvent("Event2", time2, room1, speaker1);
//        eventsManager.scheduleEvent(e2);
//        User u1 = new Attendee("user1@email.com", "password");
//        eventsManager.getEvents().get(0).addAttendees(u1.getId());
//        //test single
//        Assert.assertEquals(1, eventsManager.getUserEvents(u1.getId()).size());
//        Assert.assertTrue(eventsManager.getEvents().contains(eventsManager.getEvents().get(0)));
//        //test multiple
//        eventsManager.getEvents().get(1).addAttendees(u1.getId());
//        Assert.assertEquals(2, eventsManager.getUserEvents(u1.getId()).size());
//        Assert.assertTrue(eventsManager.getEvents().contains(eventsManager.getEvents().get(0)));
//        Assert.assertTrue(eventsManager.getEvents().contains(eventsManager.getEvents().get(1)));
//    }
//
//
//}
