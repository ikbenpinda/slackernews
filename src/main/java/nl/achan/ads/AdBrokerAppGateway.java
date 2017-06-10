package nl.achan.ads;

import com.google.gson.Gson;
import nl.achan.aggregate.interfaces.Article;
import nl.achan.aggregate.interfaces.OnArticleSubmittedCallback;
import nl.achan.jms.MessageReceiverGateway;
import nl.achan.jms.MessageSenderGateway;

import javax.jms.Message;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * x
 *
 * Created by Etienne on 10-6-2017.
 */
public class AdBrokerAppGateway {

    private int bidId = 0; // FIXME - bad threading practices. // todo - split up bid aggregate and app layer.

    // VERIFY - Loop over Sender/Receiver pairs or keep open in parallel?
    private MessageSenderGateway AdMobSender;
    private MessageReceiverGateway AdMobReceiver;
    private MessageSenderGateway DoubleClickSender;
    private MessageReceiverGateway DoubleClickReceiver;
    private MessageSenderGateway PopAdsSender;
    private MessageReceiverGateway PopAdsReceiver;

    private Gson jsonSerializer;
    private Logger logger;

    private AdvertisersRecipientList advertisers;

    private OnBidsArrivedCallback onBidsArrivedCallback;
    private OnArticleSubmittedCallback onArticleSubmittedCallback;

    public AdBrokerAppGateway(OnBidsArrivedCallback onBidsArrivedCallback, OnArticleSubmittedCallback onArticleSubmittedCallback) {
        this.onBidsArrivedCallback = onBidsArrivedCallback;
        this.onArticleSubmittedCallback = onArticleSubmittedCallback;
        jsonSerializer = new Gson();
        advertisers = new AdvertisersRecipientList();
        logger = Logger.getLogger(AdBrokerAppGateway.class.getName());
    }

    /**
     * Requests a bid from all the connected advertisers, then returns the highest bidder.
     * @param article
     * @param callback
     */
    public void requestBids(Article article, OnBidsArrivedCallback callback){
        BidRequest bid = new BidRequest(++bidId, article, new Random().nextInt(30));
        logger.log(Level.INFO, "Bid " + bid.getBidId() + " set at " + bid.getMinBid() + ", sending to advertisers.");
        String serializedBid = jsonSerializer.toJson(bid);
        for (String channel : advertisers.getAdvertisers(article)) {
            MessageSenderGateway sender = new MessageSenderGateway(channel);

            // todo - [aggregator pattern.]

            Message bidRequest = sender.createTextMessage(serializedBid);
            sender.send(bidRequest);
        }
    }
}
