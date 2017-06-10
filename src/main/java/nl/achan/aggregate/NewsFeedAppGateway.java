package nl.achan.aggregate;

import com.google.gson.Gson;
import nl.achan.ads.BidRequest;
import nl.achan.ads.AdvertisersRecipientList;
import nl.achan.aggregate.interfaces.Article;
import nl.achan.aggregate.interfaces.OnArticleSubmittedCallback;
import nl.achan.jms.MessageReceiverGateway;
import nl.achan.jms.MessageSenderGateway;
import nl.achan.ads.OnBidsArrivedCallback;

import javax.jms.Message;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gateway responsible for the serialization and messaging implementation.
 *
 * Created by Etienne on 9-6-2017.
 */
public class NewsFeedAppGateway {

    private int bidId = 0;
    private Logger logger;

    private MessageSenderGateway sender;
    private MessageReceiverGateway receiver;
    private AdvertisersRecipientList advertisers;

    private OnBidsArrivedCallback onBidsArrivedCallback;
    private OnArticleSubmittedCallback onArticleSubmittedCallback;

    private Gson jsonSerializer;

    public NewsFeedAppGateway(OnBidsArrivedCallback onBidsArrivedCallback, OnArticleSubmittedCallback onArticleSubmittedCallback) {
        this.onBidsArrivedCallback = onBidsArrivedCallback;
        this.onArticleSubmittedCallback = onArticleSubmittedCallback;
        advertisers = new AdvertisersRecipientList();
        jsonSerializer = new Gson();
        logger = Logger.getLogger(NewsFeedAppGateway.class.getName());
    }

    private void requestBids(Article article){
        BidRequest bid = new BidRequest(++bidId, article, new Random().nextInt(30));
        logger.log(Level.INFO, "Bid " + bid.getBidId() + " set at " + bid.getMinBid() + ", sending to advertisers.");
        String serializedBid = jsonSerializer.toJson(bid);
        for (String channel : advertisers.getAdvertisers(article)) {
            Message bidRequest = sender.createTextMessage(serializedBid);
            sender.send(bidRequest);
        }
    }

    private void onBidsArrived(BidRequest bid){
        onBidsArrivedCallback.execute(bid);
    }

    private void onArticleSubmitted(Article article){
        onArticleSubmittedCallback.execute(article);
    }

    private void publishArticle() {
        // todo - release article to user.
    }
}
