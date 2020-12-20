package main.gateways.converters;

import main.entities.Inbox;
import main.gateways.beans.InboxBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Serializes and de-serializes Inbox entities to and from InboxBean objects
 * An implementation of Converter
 */
public class InboxConverter implements Converter<InboxBean, Inbox> {


    public List<Inbox> convertFromBeans(List<InboxBean> inboxBeans) {
        List<Inbox> inboxes = new ArrayList<>();
        for (InboxBean inboxBean : inboxBeans) {

            Inbox inbox = new Inbox();
            inbox.setId(inboxBean.getId());
            inbox.setUser(inboxBean.getUser());
            inbox.setMessages(this.convertListFromString(inboxBean.getMessageID()));
            inboxes.add(inbox);

        }
        return inboxes;

    }

    public List<InboxBean> convertToBeans(List<Inbox> inboxes) {
       List<InboxBean> inboxBeans = new ArrayList();

       for (Inbox inbox : inboxes) {
           InboxBean inboxBean = new InboxBean();
           inboxBean.setId(inbox.getId());
           inboxBean.setUser(inbox.getUser());
           inboxBean.setMessageID(this.convertListToString(inbox.getMessages()));
           inboxBeans.add(inboxBean);
       }

        return inboxBeans;

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
