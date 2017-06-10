package nl.achan.ads.advertisers;

import nl.achan.ads.Advertiser;
import nl.achan.ads.BidReply;
import nl.achan.ads.BidRequest;
import nl.achan.jms.MessageReceiverGateway;
import nl.achan.jms.MessageSenderGateway;

import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.Random;

/**
 * Created by Etienne on 9-6-2017.
 */
public class BaseAdvertiser implements Advertiser{

    AdvertiserAppGateway gateway;

    public BaseAdvertiser() {

    }

    public BaseAdvertiser(String replyChannel, String requestChannel) {
        gateway = new AdvertiserAppGateway(replyChannel, requestChannel, this);
    }

    @Override
    public void sendBid(BidReply reply) {
        gateway.reply(reply);
    }

    @Override
    public void receiveRequest(BidRequest bid) {
        // Arbitrary generation for the returned bid.
        int amount = new Random().nextInt(20) + bid.getMinBid();
        BidReply reply = new BidReply(bid.getBidId(), amount, "example.com/ads/1");
        sendBid(reply);
    }
}
