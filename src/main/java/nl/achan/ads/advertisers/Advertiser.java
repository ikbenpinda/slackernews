package nl.achan.ads.advertisers;

import nl.achan.ads.BidReply;
import nl.achan.ads.BidRequest;

/**
 * Created by Etienne on 9-6-2017.
 */
public interface Advertiser {

    void sendBid(BidReply reply);
    void receiveRequest(BidRequest bid);
}
