package nl.achan.ads.advertisers;

import nl.achan.ads.Advertiser;
import nl.achan.ads.BidRequest;
import nl.achan.jms.MessageReceiverGateway;
import nl.achan.jms.MessageSenderGateway;
import nl.achan.monitoring.Configuration;

import javax.jms.Message;
import javax.jms.MessageListener;

import static nl.achan.monitoring.Configuration.ADVERTISER_REPLY_QUEUE_ADMOB;
import static nl.achan.monitoring.Configuration.ADVERTISER_REQUEST_QUEUE_ADMOB;

/**
 * Created by Etienne on 9-6-2017.
 */
public class AdMobAdvertiser extends BaseAdvertiser /*implements Advertiser, MessageListener*/{

    //MessageSenderGateway sender;
    //MessageReceiverGateway receiver;

    public AdMobAdvertiser() {
        super(ADVERTISER_REPLY_QUEUE_ADMOB, ADVERTISER_REQUEST_QUEUE_ADMOB);
//        sender = new MessageSenderGateway("AdMobReplyQueue");
//        receiver = new MessageReceiverGateway("AdMobRequestQueue");
    }

    @Override
    public void sendBid(BidRequest bid) {
        // send to queue
    }

    @Override
    public void onMessage(Message message) {
        // randomized amount in new bid
        // send bid
    }
}
