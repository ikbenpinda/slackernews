package nl.achan.aggregate.subscribers;

import com.google.gson.Gson;
import nl.achan.aggregate.ArticleSerializer;
import nl.achan.aggregate.interfaces.Article;
import nl.achan.aggregate.interfaces.ArticleView;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Etienne on 10-6-2017.
 */
public class UserApplicationGateway {

    private String topic;
    private MessageReceiverTopicGateway receiver;
    private ArticleSerializer serializer;
    private Logger logger;
    private OnMessageReceived callback;

    public UserApplicationGateway() {
        serializer = new ArticleSerializer();
        logger = Logger.getLogger(UserApplicationGateway.class.getName());
    }

    public void subscribe(String topic, OnMessageReceived callback){
        this.topic = topic;
        this.callback = callback;
        receiver = new MessageReceiverTopicGateway(topic);
        receiver.setListener(message -> {
            ArticleView article = null;//serializer.fromMessage(message);
            try {
                article = new Gson().fromJson( ((TextMessage) message).getText(), ArticleView.class);
            } catch (JMSException e) {
                e.printStackTrace();
            }
            logger.log(Level.INFO, "Received new article in topic: " + topic + ": " + article.toString());
            if (callback != null)
                callback.execute(article);
        });
    }

    public interface OnMessageReceived {
        void execute(ArticleView article);
    }
}
