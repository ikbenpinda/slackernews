package nl.achan.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.Consumer;

import javax.jms.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static nl.achan.monitoring.Configuration.ACTIVEMQ_PORT;
import static nl.achan.monitoring.Configuration.DEFAULT_BROKER_URL;
import static nl.achan.monitoring.Configuration.DOCKER_IP;

/**
 * We can't use a Message Driven Bean here because the mapping needs to be dynamic.
 *
 * Created by Etienne on 9-6-2017.
 */
public class MessageReceiverGateway {

    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageConsumer consumer;
    private MessageListener listener;

    public MessageReceiverGateway(String channelName) {
        // todo - setup connection and destination

        try{ // FIXME - figure out the details of the try-with that can be used here.
        ConnectionFactory factory = new ActiveMQConnectionFactory(DEFAULT_BROKER_URL); // ActiveMQ-specific.
        connection = factory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); // Non-transactional session.

        // FIXME - handle queues/topics dynamically.
        // FIXME - additional checks for channel lookup. See jndiContext.lookup(). Currently only queues are used, so this should be fine.
        destination = session.createQueue(channelName);
        consumer = session.createConsumer(destination);

        connection.start();

        } catch (JMSException e) {
            Logger.getLogger(MessageReceiverGateway.class.getName()).log(Level.SEVERE,"Failed to setup connection! :(");
            e.printStackTrace();
        }
    }

    public void setListener(MessageListener listener){
        this.listener = listener;
        if (consumer != null)
            try {
                consumer.setMessageListener(listener);
            } catch (JMSException e) {
                Logger.getLogger(MessageReceiverGateway.class.getName()).log(Level.SEVERE,"Failed to add listener! :(");
                e.printStackTrace();
            }
        else
            Logger.getLogger(MessageReceiverGateway.class.getName()).log(Level.WARNING,"Listener is null? :|");
    }
}
