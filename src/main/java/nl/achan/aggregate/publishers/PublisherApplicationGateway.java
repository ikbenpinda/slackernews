package nl.achan.aggregate.publishers;

import nl.achan.aggregate.ArticleSerializer;
import nl.achan.aggregate.interfaces.Article;
import nl.achan.jms.MessageSenderGateway;

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

    public PublisherApplicationGateway() {
        serializer = new ArticleSerializer();
    }

    public void publish(Article article) {
        setTopic(article.getCategory());
        gateway.send(gateway.createTextMessage(serializer.toMessage(article)));
    }

    private void setTopic(String topic){
        gateway = new MessageSenderTopicGateway(topic);
    }
}
