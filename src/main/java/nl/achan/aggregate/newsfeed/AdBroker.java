package nl.achan.aggregate.newsfeed;

import nl.achan.ads.OnBidsArrivedCallback;
import nl.achan.aggregate.interfaces.Article;

/**
 * Created by Etienne on 10-6-2017.
 */
public interface AdBroker {

    void findAdvertiserForArticle(Article article, OnAdvertisementResolvedCallback callback);
}
