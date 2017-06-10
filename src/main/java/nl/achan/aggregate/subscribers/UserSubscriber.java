package nl.achan.aggregate.subscribers;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * The basic listener representing an individual user.
 *
 * Created by Etienne on 9-6-2017.
 */
public class UserSubscriber implements Subscriber, MessageListener{

    UserApplicationGateway gateway;

    @Override
    public void subscribe(String topic) {

    }

    @Override
    public void onMessage(Message message) {

    }
}
