package nl.achan.ads;

/**
 * Created by Etienne on 9-6-2017.
 */
public interface Advertiser {

    void sendBid(BidReply reply);
    void receiveRequest(BidRequest bid);
}
