package nl.achan.ads.advertisers;

import static nl.achan.monitoring.Configuration.ADVERTISER_REPLY_QUEUE_POPADS;
import static nl.achan.monitoring.Configuration.ADVERTISER_REQUEST_QUEUE_POPADS;

/**
 * Created by Etienne on 9-6-2017.
 */
public class PopAdsAdvertiser extends BaseAdvertiser/*implements Advertiser, MessageListener*/{

    public PopAdsAdvertiser() {
        super(ADVERTISER_REPLY_QUEUE_POPADS, ADVERTISER_REQUEST_QUEUE_POPADS);
    }
}
