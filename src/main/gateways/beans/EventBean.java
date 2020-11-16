package main.gateways.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class EventBean implements Serializable {
    private String id;
    private String title;
    private LocalDateTime time;
    private String roomID;
    private String speakerID;
    private List<String> attendeesID;
}
