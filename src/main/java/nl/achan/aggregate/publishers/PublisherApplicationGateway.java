package nl.achan.aggregate.publishers;

import nl.achan.aggregate.ArticleSerializer;
import nl.achan.aggregate.interfaces.Article;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Application gateway for publishers.
 * One-way only, so no receivers.
 *
 * Created by Etienne on 10-6-2017.
 */
public class PublisherApplicationGateway {

    private String topic;
    private MessageSenderTopicGateway gateway;
    private ArticleSerializer serializer;
    private Logger logger;

    public PublisherApplicationGateway() {
        serializer = new ArticleSerializer();
        logger = Logger.getLogger(PublisherApplicationGateway.class.getName());
    }

    public void publish(Article article) {
        setTopic(article.getCategory());
        logger.log(Level.INFO, "Topic set: " + topic);
        gateway.send(gateway.createTextMessage(serializer.toJson(article)));
        logger.log(Level.INFO, "Article sent!");
        logger.log(Level.FINE, "Article: " + serializer.toJson(article));
    }

    private void setTopic(String topic){
        gateway = new MessageSenderTopicGateway(topic);
    }
}
