package nl.achan.aggregate.newsfeed;

import nl.achan.aggregate.ArticleSerializer;
import nl.achan.aggregate.interfaces.Article;
import nl.achan.aggregate.interfaces.ArticleView;
import nl.achan.aggregate.interfaces.OnArticleSubmittedCallback;
import nl.achan.jms.MessageSenderTopicGateway;
import nl.achan.jms.MessageReceiverGateway;

import java.util.logging.Logger;

import static nl.achan.monitoring.Configuration.PUBLISHER_QUEUE;

/**
 * Gateway responsible for the serialization and messaging implementation.
 *
 * Created by Etienne on 9-6-2017.
 */
public class NewsFeedAppGateway {

    private final Logger logger;

    /**
     * Sends messages to any topic.
     */
    private MessageSenderTopicGateway sender;

    /**
     * Receives submissions from the publishers queue.
     */
    private MessageReceiverGateway receiver;

    private final ArticleSerializer articleSerializer;

    public NewsFeedAppGateway() {
        articleSerializer = new ArticleSerializer();
        logger = Logger.getLogger(NewsFeedAppGateway.class.getName());
    }

    public void setOnArticleSubmissionCallback(OnArticleSubmittedCallback callback){
        receiver = new MessageReceiverGateway(PUBLISHER_QUEUE);
        receiver.setListener(message -> {
            Article article = articleSerializer.fromMessage(message);
            if (callback != null)
                callback.execute(article);
        });
    }

    public void publishArticle(ArticleView article, String category) {
        String channelName = category + "Topic"; // FIXME - proper lookup.
        sender = new MessageSenderTopicGateway(channelName);
        sender.send(sender.createTextMessage(articleSerializer.toJson(article)));
    }
}
