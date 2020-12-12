package main.gateways.beans;

/**
 * InboxBean is used to serialize and deserialize Inboxes
 * Each Inbox can be represented by one InboxBean
 *
 * @author David Zhao
 */
public class InboxBean {

    private String id;
    private String user;
    private String messageID;

    public InboxBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }
}
