package nl.achan.ads;

import nl.achan.aggregate.interfaces.Article;
import nl.achan.aggregate.newsfeed.AdBroker;
import nl.achan.aggregate.newsfeed.OnAdvertisementResolvedCallback;

/**
 * Created by Etienne on 10-6-2017.
 */
public class AdBrokerImpl implements AdBroker {

    @Override
    public void findAdvertiserForArticle(Article article, OnAdvertisementResolvedCallback callback) {

    }

}
