package main.usecases;

import main.entities.Event;

/**
 * The EventFactory get different additional constraints to the scheduling for various types of events.
 *
 * @author Haoze Huang
 * @version 1.0
 * @since 2020-11-27
 */
public class EventFactory {
    private final EventBuilder eb;

    public EventFactory(EventBuilder eb){
        this.eb = eb;
    }

    public Event getEvent(String type){
        switch (type) {
            case "OneSpeakerEvent":
                return eb.toOneSpeakerEvent();
            case "MultiSpeakerEvent":
                return eb.toMultiSpeakerEvent();
            default:
                return eb.toDefaultEvent();
        }
    }


}