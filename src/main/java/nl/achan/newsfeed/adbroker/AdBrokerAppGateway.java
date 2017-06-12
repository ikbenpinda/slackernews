package nl.achan.newsfeed.adbroker;

import com.google.gson.Gson;
import nl.achan.ads.BidReply;
import nl.achan.ads.BidRequest;
import nl.achan.ads.OnBidsArrivedCallback;
import nl.achan.util.domain.Article;
import nl.achan.util.jms.MessageReceiverGateway;
import nl.achan.util.jms.MessageSenderGateway;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static nl.achan.util.Configuration.ADVERTISER_REPLY_QUEUE;

/**
 *
 *
 * Created by Etienne on 10-6-2017.
 */
public class AdBrokerAppGateway {

    // VERIFY - Loop over Sender/Receiver pairs or keep open in parallel?
//    private MessageSenderGateway AdMobSender;
//    private MessageReceiverGateway AdMobReceiver;
//    private MessageSenderGateway DoubleClickSender;
//    private MessageReceiverGateway DoubleClickReceiver;
//    private MessageSenderGateway PopAdsSender;
//    private MessageReceiverGateway PopAdsReceiver;
    private MessageReceiverGateway receiver;

    private Gson jsonSerializer;
    private Logger logger;

    private AdvertisersRecipientList advertisers;
    private Map<Long, Aggregator> aggregators;

    public AdBrokerAppGateway() {
        jsonSerializer = new Gson();
        advertisers = new AdvertisersRecipientList();
        aggregators = new HashMap<>();
        logger = Logger.getLogger(AdBrokerAppGateway.class.getName());
        receiver = new MessageReceiverGateway(ADVERTISER_REPLY_QUEUE);
        receiver.setListener(message -> {
            // deserialize message
            BidReply reply = deserialize(message);

            // delegate to the related aggregator if available.
            aggregators.get(reply.getBidId()).addMessage(reply); // todo - check for NPE
        });
    }

    private BidReply deserialize(Message message){
        try {
            TextMessage textMessage = ((TextMessage) message);
            BidReply reply = jsonSerializer.fromJson(textMessage.getText(), BidReply.class);
            return reply;
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Requests a bid from all the connected advertisers, then returns the highest bidder.
     * @param article
     * @param callback
     */
    public void requestBids(Article article, OnBidsArrivedCallback callback){

        BidRequest bid = new BidRequest(article.getArticleId(), article, new Random().nextInt(30));
        String serializedBid = jsonSerializer.toJson(bid);
        logger.log(Level.INFO, "Bid " + bid.getBidId() + " set at " + bid.getMinBid() + ", sending to advertisers.");

        List<String> channels = advertisers.getAdvertisers(article);

        // Create an aggregator to handle asynchronous gathering of bids.
        Aggregator aggregator = new Aggregator(article.getArticleId(), channels.size());
        aggregator.setCallback(reply -> {
//                if (aggregator.isComplete()) {
//                    if (callback != null)
//                        callback.execute(reply);

            // Notify the service of the completed aggregation.
            callback.execute(aggregator.getBestResult());
            // Finally, tell it to unregister itself
            // from the list of active aggregators.
            aggregators.remove(article.getArticleId());
//                }
//                else
//                    aggregator.addMessage(reply); // fixme - before or after in flow
        });
        aggregators.put(article.getArticleId(), aggregator);

        for (String channel : channels) {
            MessageSenderGateway sender = new MessageSenderGateway(channel);
            Message bidRequest = sender.createTextMessage(serializedBid);
            sender.send(bidRequest);
        }

    }
}
