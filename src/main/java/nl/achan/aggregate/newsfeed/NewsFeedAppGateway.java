package nl.achan.aggregate.newsfeed;

import com.google.gson.Gson;
import nl.achan.ads.BidRequest;
import nl.achan.ads.AdvertisersRecipientList;
import nl.achan.aggregate.ArticleSerializer;
import nl.achan.aggregate.interfaces.Article;
import nl.achan.aggregate.interfaces.ArticleView;
import nl.achan.aggregate.interfaces.OnArticleSubmittedCallback;
import nl.achan.jms.MessageReceiverGateway;
import nl.achan.jms.MessageSenderGateway;
import nl.achan.ads.OnBidsArrivedCallback;

import javax.jms.Message;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static nl.achan.monitoring.Configuration.PUBLISHER_QUEUE;

/**
 * Gateway responsible for the serialization and messaging implementation.
 *
 * Created by Etienne on 9-6-2017.
 */
public class NewsFeedAppGateway {

//    private int bidId = 0; // FIXME - bad threading practices. // todo - split up bid aggregate and app layer.
    private Logger logger;

//    private MessageSenderGateway sender;
    private MessageReceiverGateway receiver;
//    private AdvertisersRecipientList advertisers;

//    private OnBidsArrivedCallback onBidsArrivedCallback;
//    private OnArticleSubmittedCallback onArticleSubmittedCallback;

    private ArticleSerializer articleSerializer;
    private Gson jsonSerializer;

    public NewsFeedAppGateway() {
//        this.onBidsArrivedCallback = onBidsArrivedCallback;
//        this.onArticleSubmittedCallback = onArticleSubmittedCallback;
//        advertisers = new AdvertisersRecipientList();



        articleSerializer = new ArticleSerializer();
        jsonSerializer = new Gson();
        logger = Logger.getLogger(NewsFeedAppGateway.class.getName());
    }
//
//    public void requestBids(ArticleView article, OnBidsArrivedCallback callback){
//        BidRequest bid = new BidRequest(++bidId, article.getArticleLink(), new Random().nextInt(30));
//        logger.log(Level.INFO, "Bid " + bid.getBidId() + " set at " + bid.getMinBid() + ", sending to advertisers.");
//        String serializedBid = jsonSerializer.toJson(bid);
//        for (String channel : advertisers.getAdvertisers(article)) {
//            Message bidRequest = sender.createTextMessage(serializedBid);
//            sender.send(bidRequest);
//        }
//    }

    public void setOnArticleSubmissionCallback(OnArticleSubmittedCallback callback){
        receiver = new MessageReceiverGateway(PUBLISHER_QUEUE);
        receiver.setListener(message -> {
            Article article = articleSerializer.fromMessage(message);
            if (callback != null)
                callback.execute(article);
        });
    }

    public void publishArticle(ArticleView article) {

        String channelName = article.getArticleLink().getCategory() + "Topic"; // FIXME - proper lookup.
        sender = new MessageSenderGateway(channelName);
        sender.send(sender.createTextMessage(articleSerializer.toJson(article)));
    }
}
