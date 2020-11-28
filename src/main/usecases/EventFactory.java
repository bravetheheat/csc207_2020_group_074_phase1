package main.usecases;

import main.entities.Event;

import java.time.LocalDateTime;

/**
 * The EventFactory get different additional constraints to the scheduling for various types of events.
 *
 * @author Haoze Huang
 * @version 1.0
 * @since 2020-11-27
 */
public class EventFactory {
    private final String title;
    private final LocalDateTime time;
    private final String roomID;
    private String requirement;
    private final int duration;

    public EventFactory(String title, LocalDateTime time, String roomID, String requirement, int duration){
        this.title = title;
        this.time = time;
        this.roomID = roomID;
        this.requirement = requirement;
        this.duration = duration;
    }

    public Event getEvent(String type){
        EventBuilder eb = new EventBuilder();
        eb.setTitle(title);
        eb.setRoom(roomID);
        eb.setTime(time);
        eb.setRequirement(requirement);
        eb.setDuration(duration);
        if (type == null){
            return eb.toDefaultEvent();
        }else if (type.equals("One")){
            return eb.toOneSpeakerEvent();
        }else if (type.equals("Multi")) {
            return eb.toMultiSpeakerEvent();
        }else{
            return eb.toDefaultEvent();
        }
    }


}