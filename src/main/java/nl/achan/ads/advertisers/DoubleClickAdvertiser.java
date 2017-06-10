package nl.achan.ads.advertisers;

import static nl.achan.monitoring.Configuration.ADVERTISER_REPLY_QUEUE_DOUBLECLICK;
import static nl.achan.monitoring.Configuration.ADVERTISER_REQUEST_QUEUE_DOUBLECLICK;

/**
 * Created by Etienne on 9-6-2017.
 */
public class DoubleClickAdvertiser extends BaseAdvertiser/*implements Advertiser, MessageListener*/{

    public DoubleClickAdvertiser() {
        super(ADVERTISER_REPLY_QUEUE_DOUBLECLICK, ADVERTISER_REQUEST_QUEUE_DOUBLECLICK);
    }
}
