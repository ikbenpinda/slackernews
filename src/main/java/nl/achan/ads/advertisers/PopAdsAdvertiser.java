package nl.achan.ads.advertisers;

import nl.achan.ads.Advertiser;
import nl.achan.ads.BidReply;
import nl.achan.ads.BidRequest;
import nl.achan.jms.MessageReceiverGateway;
import nl.achan.jms.MessageSenderGateway;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by Etienne on 9-6-2017.
 */
public class PopAdsAdvertiser extends BaseAdvertiser/*implements Advertiser, MessageListener*/{

//    MessageSenderGateway sender;
//    MessageReceiverGateway receiver;

    public PopAdsAdvertiser() {
        super("PopAdsReplyQueue", "PopAdsRequestQueue");
//        sender = new MessageSenderGateway("PopAdsReplyQueue");
//        receiver = new MessageReceiverGateway("PopAdsRequestQueue");
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
