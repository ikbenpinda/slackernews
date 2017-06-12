package nl.achan.newsfeed.adbroker;

import nl.achan.util.domain.Article;
import nl.achan.newsfeed.OnAdvertisementResolvedCallback;

/**
 * Created by Etienne on 10-6-2017.
 */
public interface AdBroker {

    void findAdvertiserForArticle(Article article, OnAdvertisementResolvedCallback callback);
}
