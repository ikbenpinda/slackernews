package nl.achan.ads;

import java.io.Serializable;

/**
 * Bids as returned by the advertisers.
 *
 * Created by Etienne on 9-6-2017.
 */
public class BidReply implements Serializable{

    /**
     * Reference to the bid.
     */
    private int bidId;

    /**
     * The offer made by the advertiser.
     */
    private int bid;

    /**
     * The link to the ad to be displayed.
     */
    private String adLink;

    public BidReply() {
    }

    public BidReply(int bidId, int bid, String adLink) {
        this.bidId = bidId;
        this.bid = bid;
        this.adLink = adLink;
    }

    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getAdLink() {
        return adLink;
    }

    public void setAdLink(String adLink) {
        this.adLink = adLink;
    }

    @Override
    public String toString() {
        return "BidReply{" +
                "bidId=" + bidId +
                ", bid=" + bid +
                ", adLink='" + adLink + '\'' +
                '}';
    }
}
