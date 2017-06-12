package nl.achan.util.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static nl.achan.util.Configuration.DEFAULT_BROKER_URL;

/**
 * Created by Etienne on 10-6-2017.
 */
public class MessageSenderTopicGateway {

    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer producer;
    private Logger logger;

    public MessageSenderTopicGateway(String channelName) {

        logger = Logger.getLogger(MessageReceiverGateway.class.getName());

        try { // FIXME - figure out the details of the try-with that can be used here.
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_URL);
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic(channelName);
            producer = session.createProducer(destination);

            connection.start();
        }
        catch(JMSException e){
            logger.log(Level.SEVERE,"Failed to setup connection! :(");
            e.printStackTrace();
        }
    }

    public Message createTextMessage(String body){
        try {
            TextMessage textMessage = session.createTextMessage(body);
            return textMessage;
        } catch (JMSException e) {
            logger.log(Level.SEVERE,"Failed to parse message! :(");
            e.printStackTrace();
            return null;
        }
    }

    public void send(Message message){
        try {
            producer.send(destination, message);
        } catch (JMSException e) {
            logger.log(Level.SEVERE,"Failed to send message! :(");
            e.printStackTrace();
        }
    }
}
