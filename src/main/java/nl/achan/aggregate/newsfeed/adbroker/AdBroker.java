package nl.achan.aggregate.newsfeed.adbroker;

import nl.achan.ads.OnBidsArrivedCallback;
import nl.achan.aggregate.interfaces.Article;
import nl.achan.aggregate.newsfeed.OnAdvertisementResolvedCallback;

/**
 * Created by Etienne on 10-6-2017.
 */
public interface AdBroker {

    void findAdvertiserForArticle(Article article, OnAdvertisementResolvedCallback callback);
}
