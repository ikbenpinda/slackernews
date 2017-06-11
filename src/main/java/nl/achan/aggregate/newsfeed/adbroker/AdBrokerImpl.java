package nl.achan.aggregate.newsfeed.adbroker;

import nl.achan.aggregate.interfaces.Article;
import nl.achan.aggregate.newsfeed.NewsFeed;
import nl.achan.aggregate.newsfeed.OnAdvertisementResolvedCallback;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * x
 *
 * Created by Etienne on 10-6-2017.
 */
public class AdBrokerImpl implements AdBroker {

    private AdBrokerAppGateway gateway;
    private Logger logger;

    public AdBrokerImpl() {
        gateway = new AdBrokerAppGateway();
        logger = Logger.getLogger(AdBrokerImpl.class.getName());
    }

    @Override
    public void findAdvertiserForArticle(Article article, OnAdvertisementResolvedCallback callback) {
        gateway.requestBids(article, bid -> {
            logger.log(Level.INFO, "Adspace resolved! Final bid at " + bid.getBid() + " for link: " + bid.getAdLink());
            callback.execute(bid.getAdLink());
        });
    }

}
