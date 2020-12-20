package main.gateways.converters;

import main.entities.Event;
import main.gateways.beans.EventBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Serializes and de-serializes Event entities to and from EventBean objects
 * An implementation of Converter
 *
 * @author David Zhao
 */
public class EventConverter implements Converter<EventBean, Event> {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public List<Event> convertFromBeans(List<EventBean> eventBeans) {
        List<Event> events = new ArrayList<>();

        for (EventBean eventBean : eventBeans) {
            Event event = new Event();
            event.setId(eventBean.getId());
            event.setRoomID(eventBean.getRoomID());
            event.setTitle(eventBean.getTitle());
            event.setTime(LocalDateTime.parse(eventBean.getTime(), this.dateTimeFormatter));
            event.setType(eventBean.getType());
            event.setDuration(eventBean.getDuration());
            event.setCapacity(eventBean.getCapacity());

            for (String attendeeID : this.convertListFromString(eventBean.getAttendeesId())) {
                event.addAttendees(attendeeID);
            }

            for (String speakerID : this.convertListFromString(eventBean.getSpeakersID())) {
                event.addSpeaker(speakerID);
            }

            events.add(event);
        }

        return events;
    }

    public List<EventBean> convertToBeans(List<Event> events) {
        List<EventBean> eventBeanList = new ArrayList<>();

        for (Event event : events) {
            EventBean eventBean = new EventBean();
            eventBean.setRoomID(event.getRoomID());
            eventBean.setId(event.getId());
            eventBean.setTitle(event.getTitle());
            eventBean.setType(event.getType());
            eventBean.setCapacity(event.getCapacity());
            eventBean.setDuration(event.getDuration());
            eventBean.setTime(event.getTime().format(this.dateTimeFormatter));
            eventBean.setAttendeesId(this.convertListToString(event.getAttendeesID()));
            eventBean.setSpeakersID(this.convertListToString(event.getSpeakers()));
            eventBeanList.add(eventBean);
        }
        return eventBeanList;

    }

    private String convertListToString(List<String> list) {

        String string = String.join("|", list);
        return string;
    }

    private List<String> convertListFromString(String string) {

        List<String> list = new ArrayList<>(Arrays.asList(string.split("[\\|]", -1)));

        return list;
    }

}
