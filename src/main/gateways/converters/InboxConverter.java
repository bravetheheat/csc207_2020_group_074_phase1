package main.gateways.converters;

import main.entities.Inbox;
import main.gateways.beans.InboxBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InboxConverter implements Converter<InboxBean, Inbox> {


    public List<Inbox> convertFromBeans(List<InboxBean> inboxBeans) {
        Map<String, Inbox> inboxes = new HashMap<>();

        for (InboxBean inboxBean : inboxBeans) {

            Inbox currentInbox = inboxes.get(inboxBean.getId());
            if (currentInbox == null) {
                currentInbox = new Inbox();
                currentInbox.setId(inboxBean.getId());
                currentInbox.setUser(inboxBean.getUser());
                inboxes.put(currentInbox.getId(), currentInbox);

            }
            currentInbox.addMessage(inboxBean.getMessageID());
        }
        List<Inbox> inboxList = new ArrayList<>();
        inboxList.addAll(inboxes.values());
        return inboxList;

    }

    public List<InboxBean> convertToBeans(List<Inbox> inboxes) {
        List<InboxBean> inboxBeanList = new ArrayList<>();
        for (Inbox inbox : inboxes) {
            if (inbox.getMessages().size() > 0) {
                for (String messageID : inbox.getMessages()) {
                    InboxBean inboxBean = new InboxBean();
                    inboxBean.setId(inbox.getId());
                    inboxBean.setUser(inbox.getUser());
                    inboxBean.setMessageID(messageID);
                    inboxBeanList.add(inboxBean);
                }
            } else {

                InboxBean inboxBean = new InboxBean();
                inboxBean.setId(inbox.getId());
                inboxBean.setUser(inbox.getUser());
                inboxBeanList.add(inboxBean);
            }
        }

        return inboxBeanList;

    }


}
