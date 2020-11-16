package main.gateways.converters;

import main.entities.Event;
import main.gateways.beans.EventBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventConverter implements Converter{

    public List<Event> convertFromBeans(List<EventBean> eventBeans) {
        Map<String, Event> events = new HashMap<>();

        for (EventBean eventBean : eventBeans) {
            Event event = events.get(eventBean.getId());
            if (event == null) {
                event = new Event();
                event.setId(eventBean.getId());
                event.setRoomID(eventBean.getRoomID());
                event.setSpeakerID(eventBean.getSpeakerID());
                event.setTitle(eventBean.getTitle());
                event.setTime(eventBean.getTime());
                events.put(event.getId(), event);
            }

            event.addAttendees(eventBean.getAttendeeId());

        }

        List<Event> eventList = new ArrayList<>();
        eventList.addAll(events.values());

        return eventList;
    }

    public List<EventBean> convertToBeans(List<Event> events) {
        List<EventBean> eventBeanList = new ArrayList<>();

        for (Event event : events) {
            if (event.getAttendeesID().size() > 0) {
                for (String attendeeId : event.getAttendeesID()) {
                    EventBean eventBean = new EventBean();
                    eventBean.setId(event.getId());
                    eventBean.setRoomID(event.getRoomID());
                    eventBean.setSpeakerID(event.getSpeakerID());
                    eventBean.setTitle(event.getTitle());
                    eventBean.setTime(event.getTime());
                    eventBean.setAttendeeId(attendeeId);
                    eventBeanList.add(eventBean);
                }
            } else {
                EventBean eventBean = new EventBean();
                eventBean.setId(event.getId());
                eventBean.setRoomID(event.getRoomID());
                eventBean.setSpeakerID(event.getSpeakerID());
                eventBean.setTitle(event.getTitle());
                eventBean.setTime(event.getTime());
                eventBeanList.add(eventBean);
            }


        }
        return eventBeanList;

    }
}
