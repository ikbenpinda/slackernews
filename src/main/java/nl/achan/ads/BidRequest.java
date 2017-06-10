package nl.achan.ads;

import nl.achan.aggregate.interfaces.Article;

import java.io.Serializable;

/**
 * A basic structure for advertisement bidding.
 *
 * Created by Etienne on 9-6-2017.
 */
public class BidRequest implements Serializable{

    /**
     * Identifier for the bid itself.
     */
    private int bidId;

    /**
     * Related article
     */
    private Article article;

    /**
     * Minimum amount for the bid.
     */
    private int minBid;

    public BidRequest() {
    }

    public BidRequest(int bidId, Article article, int minBid) {
        this.bidId = bidId;
        this.article = article;
        this.minBid = minBid;
    }

    public int getBidId() {
        return bidId;
    }

    public Article getArticle() {
        return article;
    }

    public int getMinBid() {
        return minBid;
    }

    @Override
    public String toString() {
        return "BidRequest{" +
                "bidId=" + bidId +
                ", article=" + article +
                ", minBid=" + minBid +
                '}';
    }
}
