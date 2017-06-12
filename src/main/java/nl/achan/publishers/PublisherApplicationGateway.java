package nl.achan.publishers;

import nl.achan.util.ArticleSerializer;
import nl.achan.util.domain.Article;
import nl.achan.util.jms.MessageSenderGateway;
import nl.achan.util.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Application gateway for publishers.
 * One-way only, so no receivers.
 *
 * Created by Etienne on 10-6-2017.
 */
public class PublisherApplicationGateway {

    private MessageSenderGateway gateway;
    private ArticleSerializer serializer;
    private Logger logger;

    public PublisherApplicationGateway() {
        serializer = new ArticleSerializer();
        logger = Logger.getLogger(PublisherApplicationGateway.class.getName());
    }

    public void publish(Article article) {
        gateway = new MessageSenderGateway(Configuration.PUBLISHER_QUEUE);
        gateway.send(gateway.createTextMessage(serializer.toJson(article)));
        logger.log(Level.INFO, "Article sent!");
        logger.log(Level.FINE, "Article: " + serializer.toJson(article));
    }
}
