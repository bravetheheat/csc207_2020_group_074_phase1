package main.gateways.converters;

import main.entities.Message;
import main.gateways.beans.MessageBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MessageConverter implements Converter<MessageBean, Message> {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public List<Message> convertFromBeans(List<MessageBean> messageBeans) {
        List<Message> messages = new ArrayList<>();

        for (MessageBean messageBean : messageBeans) {
            Message message = new Message();
            message.setId(messageBean.getId());
            message.setText(messageBean.getText());
            message.setSender(messageBean.getSender());
            message.setTime(LocalDateTime.parse(messageBean.getTime(), this.dateTimeFormatter));

        }
        return messages;

    }

    public List<MessageBean> convertToBeans(List<Message> messages) {
        List<MessageBean> messageBeanList = new ArrayList<>();

        for (Message message : messages) {
            MessageBean messageBean = new MessageBean();
            messageBean.setId(message.getId());
            messageBean.setSender(message.getSender());
            messageBean.setTime(message.getTime().format(this.dateTimeFormatter));
            messageBean.setText(message.getText());

            messageBeanList.add(messageBean);
        }

        return messageBeanList;

    }
}
