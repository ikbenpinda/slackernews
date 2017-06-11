package nl.achan.aggregate.newsfeed.adbroker;

import nl.achan.aggregate.interfaces.Article;
import nl.achan.aggregate.Categories;

import java.util.ArrayList;
import java.util.List;

import static nl.achan.monitoring.Configuration.ADVERTISER_REQUEST_QUEUE_ADMOB;
import static nl.achan.monitoring.Configuration.ADVERTISER_REQUEST_QUEUE_DOUBLECLICK;
import static nl.achan.monitoring.Configuration.ADVERTISER_REQUEST_QUEUE_POPADS;

/**
 * Implementation for the Recipient List pattern.
 * Contains all logic for determining the advertisers to send a request to.
 *
 * Created by Etienne on 9-6-2017.
 */
public class AdvertisersRecipientList {

    /**
     * Returns a list of advertisers to request a bid from.
     * @param article the article to request bids for.
     * @return a list of channel names of interested advertisers.
     */
    public List<String> getAdvertisers(Article article) {

        List<String> channels = new ArrayList<>();

        if (article.getCategory().equals(Categories.GAMING) || article.getCategory().equals(Categories.SPORTS))
            channels.add(ADVERTISER_REQUEST_QUEUE_ADMOB);

        channels.add(ADVERTISER_REQUEST_QUEUE_DOUBLECLICK);
        channels.add(ADVERTISER_REQUEST_QUEUE_POPADS);

        return channels;
    }
}
