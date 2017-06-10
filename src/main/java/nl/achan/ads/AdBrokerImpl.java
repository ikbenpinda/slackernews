package nl.achan.ads;

import nl.achan.aggregate.interfaces.Article;
import nl.achan.aggregate.newsfeed.AdBroker;
import nl.achan.aggregate.newsfeed.OnAdvertisementResolvedCallback;

/**
 * x
 *
 * Created by Etienne on 10-6-2017.
 */
public class AdBrokerImpl implements AdBroker {

    AdBrokerAppGateway gateway;

    public AdBrokerImpl() {
        gateway = new AdBrokerAppGateway();
    }

    @Override
    public void findAdvertiserForArticle(Article article, OnAdvertisementResolvedCallback callback) {
        // scatter-gather magic.
    }

}
