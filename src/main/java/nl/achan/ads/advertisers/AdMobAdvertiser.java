package nl.achan.ads.advertisers;

import static nl.achan.monitoring.Configuration.ADVERTISER_REPLY_QUEUE_ADMOB;
import static nl.achan.monitoring.Configuration.ADVERTISER_REQUEST_QUEUE_ADMOB;

/**
 * Created by Etienne on 9-6-2017.
 */
public class AdMobAdvertiser extends BaseAdvertiser /*implements Advertiser, MessageListener*/{

    public AdMobAdvertiser() {
        super(ADVERTISER_REQUEST_QUEUE_ADMOB);
    }
}
