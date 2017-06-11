package nl.achan.ads.advertisers;

import nl.achan.ads.BidReply;
import nl.achan.ads.BidRequest;

import java.util.Random;

/**
 * Generic class for easy creation of additional advertisers.
 * Advertisers simply receive requests, do an arbitrary calculation, and reply.
 * This means a lot of code can be abstracted, but also a slightly tighter coupling between classes.
 *
 * Created by Etienne on 9-6-2017.
 */
public abstract class BaseAdvertiser implements Advertiser{

    private AdvertiserAppGateway gateway;

    public BaseAdvertiser() {
    }

    /**
     * Creates a new advertiser.
     * @param requestChannel the name of the queue for incoming adspace bidding requests.
     */
    public BaseAdvertiser(String requestChannel) {
        gateway = new AdvertiserAppGateway(requestChannel, this);
    }

    /**
     * Sends a reply back to the ad broker with the final bid.
     */
    @Override
    public void sendBid(BidReply reply) {
        gateway.reply(reply);
    }

    /**
     * Called when an available adspace is up for bidding.
     * @param bid the original request.
     */
    @Override
    public void receiveRequest(BidRequest bid){
        // Arbitrary generation for the returned bid.
        // This can be overridden from the child classes, or left here for increased laziness.
        int amount = new Random().nextInt(20) + bid.getMinBid();
        BidReply reply = new BidReply(bid.getBidId(), amount, "example.com/ads/" + new Random().nextInt(10000));
        sendBid(reply);
    }
}
