//import main.entities.Attendee;
//import main.entities.Event;
//import main.entities.Room;
//import main.entities.Speaker;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class EventTest {
//   Room room;
//   Speaker speaker;
//   LocalDateTime time;
//   Event event;
//
//   @Before
//   public void setUp(){
//       room = new Room();
//       speaker = new Speaker("speaker@gmail.com", "123");
//       time = LocalDateTime.now();
//       event = new Event("Party", time, room.getId(),speaker.getId());
//   }
//
//
//   @Test
//   public void testEventAddAttendees(){
//       List<String> attendees = event.getAttendeesID();
//       Attendee attendee1 = new Attendee("attendee@gmail.com", "123");
//       event.addAttendees(attendee1.getId());
//       List<String> expected = new ArrayList<>();
//       expected.add(attendee1.getId());
//       Assert.assertEquals(expected, attendees);
//    }
//
//
//
//}
