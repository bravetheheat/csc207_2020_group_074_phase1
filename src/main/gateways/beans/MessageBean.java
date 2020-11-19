package main.gateways.beans;

/**
 * MesssageBean is used to serialize and deserialize Messsages
 * Each Messsage can be represented by one MesssageBean
 *
 * @author David Zhao
 */
public class MessageBean {

    private String id;
    private String text;
    private String time;
    private String sender;

    public MessageBean() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
