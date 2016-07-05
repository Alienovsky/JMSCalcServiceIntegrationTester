package com.madebykamil

import com.madebykamil.jms.MessageConsumer
import com.madebykamil.jms.MessageSender
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(locations = "classpath:application-context.xml")
class TestAdd extends Specification {
    @Autowired
    MessageSender messageSender;

    @Autowired
    MessageConsumer messageConsumer;

    @Test
    public "test test"() {
        when:
        messageSender.send("2,2,*");
        then:
        this.sleep(1000);
        messageConsumer.getResult() == new Float(2 * 2);
    }

    @Test
    public "test test4"() {
        when:
        messageSender.send("2,2,/");
        then:
        this.sleep(1000);
        messageConsumer.getResult() == new Float(2 / 2);
    }

    @Test
    public "test test3"() {
        when:
        messageSender.send("2,2,+");
        then:
        this.sleep(1000);
        messageConsumer.getResult() == new Float(2 + 2);
    }

    @Test
    public "test test2"() {
        when:
        messageSender.send("2,2,-");
        then:
        this.sleep(1000);
        messageConsumer.getResult() == new Float(2 - 2);
    }

    @Test
    @Unroll("#x * #y == #result?")
    public "test test when"() {
        given:
        messageSender.send(x + "," + y + ",*");
        this.sleep(1000);
        expect:
        messageConsumer.result == result;
        where:
        x  | y  | result
        2  | 2  | 4.0
        3  | 3  | 9.0
        4  | 4  | 16.0
        5  | 5  | 25.0
        -3 | -2 | 6.0
    }

}
