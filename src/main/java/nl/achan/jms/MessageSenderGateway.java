package nl.achan.jms;

import com.google.gson.Gson;
import nl.achan.monitoring.Configuration;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.Producer;

import javax.jms.*;
import javax.ws.rs.Produces;

import java.util.logging.Level;
import java.util.logging.Logger;

import static nl.achan.monitoring.Configuration.ACTIVEMQ_PORT;
import static nl.achan.monitoring.Configuration.DEFAULT_BROKER_URL;
import static nl.achan.monitoring.Configuration.DOCKER_IP;

/**
 * Created by Etienne on 9-6-2017.
 */
public class MessageSenderGateway {

    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer producer;
    private Logger logger;

    public MessageSenderGateway(String channelName) {

        try { // FIXME - figure out the details of the try-with that can be used here.
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_URL);
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(channelName);
            producer = session.createProducer(destination);
            logger = Logger.getLogger(MessageReceiverGateway.class.getName());

            connection.start();
        }
        catch(JMSException e){
            logger.log(Level.SEVERE,"Failed to setup connection! :(");
            e.printStackTrace();
        }
    }

    public Message createTextMessage(String body){
        try {
            TextMessage textMessage = (TextMessage) session.createTextMessage(body);
            return textMessage;
        } catch (JMSException e) {
            logger.log(Level.SEVERE, "Failed to parse message! :(");
            e.printStackTrace();
            return null;
        }
    }

    public void send(Message message){
        try {
            producer.send(destination, message);
        } catch (JMSException e) {
            logger.log(Level.SEVERE, "Failed to send message! :(");
            e.printStackTrace();
        }
    }
}
