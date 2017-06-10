package nl.achan.aggregate.subscribers;

import nl.achan.aggregate.ArticleSerializer;
import nl.achan.aggregate.interfaces.Article;

import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Etienne on 10-6-2017.
 */
public class UserApplicationGateway implements MessageListener {

    private String topic;
    private MessageReceiverTopicGateway receiver;
    private ArticleSerializer serializer;

    public UserApplicationGateway(String topic) {
        this.topic = topic;
        receiver = new MessageReceiverTopicGateway(topic);
        receiver.setListener(this);
        serializer = new ArticleSerializer();
    }

    void onMessageReceived(Article article){
        Logger.getLogger(UserApplicationGateway.class.getName()).log(Level.INFO, "Received new article in topic: " + topic + ": " + article.toString());
    }

    void setTopic(String topic){
        this.topic = topic;
        receiver = new MessageReceiverTopicGateway(topic);
        receiver.setListener(this);
    }

    @Override
    public void onMessage(Message message) {
        onMessageReceived(serializer.fromMessage(message));
    }
}
