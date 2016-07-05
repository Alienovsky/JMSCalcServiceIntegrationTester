package com.madebykamil.jms;

import javax.jms.*;

public class MessageConsumer implements MessageListener {

    private MessageSender messageSender;

    public Float getResult() {
        return Float.parseFloat(result);
    }

    private void setResult(String result){
        System.out.println("Setting result with value: "+result);
        this.result=result;
    }

    private String result;

    public void onMessage(final Message message) {
        if(message instanceof TextMessage){
            final TextMessage textMessage = (TextMessage) message;
            try {
                setResult(textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
