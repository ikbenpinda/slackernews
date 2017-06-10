package nl.achan.ads.advertisers;

import nl.achan.ads.Advertiser;
import nl.achan.ads.BidReply;
import nl.achan.ads.BidRequest;
import nl.achan.jms.MessageReceiverGateway;
import nl.achan.jms.MessageSenderGateway;

import javax.jms.Message;
import javax.jms.MessageListener;

import static nl.achan.monitoring.Configuration.ADVERTISER_REPLY_QUEUE_DOUBLECLICK;
import static nl.achan.monitoring.Configuration.ADVERTISER_REQUEST_QUEUE_DOUBLECLICK;

/**
 * Created by Etienne on 9-6-2017.
 */
public class DoubleClickAdvertiser extends BaseAdvertiser/*implements Advertiser, MessageListener*/{

    //MessageSenderGateway sender;
    //MessageReceiverGateway receiver;

    public DoubleClickAdvertiser() {
        super(ADVERTISER_REPLY_QUEUE_DOUBLECLICK, ADVERTISER_REQUEST_QUEUE_DOUBLECLICK);
        //sender = new MessageSenderGateway("DoubleClickReplyQueue");
        //receiver = new MessageReceiverGateway("DoubleClickRequestQueue");
    }

    @Override
    public void sendBid(BidReply reply) {
        super.sendBid(reply);
    }

    @Override
    public void receiveRequest(BidRequest bid) {
        super.receiveRequest(bid);
    }
}
