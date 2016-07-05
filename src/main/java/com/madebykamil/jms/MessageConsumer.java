package com.madebykamil.jms;

import javax.jms.*;

public class MessageConsumer implements MessageListener {

    private MessageSender messageSender;

    public Float getResult() {
        return Float.parseFloat(result);
    }

    private String result;

    public void onMessage(final Message message) {
        if(message instanceof TextMessage){
            final TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("Result is: " + textMessage.getText());
                result = textMessage.getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        if (message instanceof MapMessage) {
            final MapMessage mapMessage = (MapMessage) message;
            try {
                System.out.println(mapMessage.getMapNames());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
