package nl.achan.ads;

import nl.achan.aggregate.interfaces.Article;

/**
 * Created by Etienne on 9-6-2017.
 */
public interface BidAggregator { // newsfeed does an async to the ad manager

    void scatterBids(Article article);
    void gatherBids();

}
