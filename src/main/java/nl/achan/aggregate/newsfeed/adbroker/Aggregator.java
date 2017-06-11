package nl.achan.aggregate.newsfeed.adbroker;

import nl.achan.ads.BidReply;
import nl.achan.ads.OnBidsArrivedCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * todo - figure this out.
 *
 * Created by Etienne on 9-6-2017.
 */
public class Aggregator {

    private long id;
    private int expectedMessages;
    //Object ACT; // todo - keeps track of state, some kind of token.
    private BidReply bestBid;
    private OnBidsArrivedCallback callback;
    private List<BidReply> replies;

    public Aggregator(long id, int expectedMessages/*, Object ACT, OnBidsArrivedCallback callback*/) {
        this.id = id;
        this.expectedMessages = expectedMessages;
        replies = new ArrayList<>();
//        this.ACT = ACT;
//        this.callback = callback;
    }

    public void setCallback(OnBidsArrivedCallback callback) {
        this.callback = callback;
    }

    public void addMessage(BidReply reply)
    {
        replies.add(reply);

        // If uninterested advertisers wasn't already opted-out
        // through the recipient list,
        // they can reply with an empty bid.
        if (reply.getBid() <= 0)
            return;

        if (bestBid == null || reply.getBid() > bestBid.getBid())
            bestBid = reply;

        if (isComplete())
            if (callback != null)
                callback.execute(reply);
    }

    public boolean isComplete(){
        return replies.size() == expectedMessages;
    }

    public BidReply getBestResult(){
        return bestBid;
    }

    public void notifyBestResult(){
        if (callback != null)
            callback.execute(bestBid);
    }
}
