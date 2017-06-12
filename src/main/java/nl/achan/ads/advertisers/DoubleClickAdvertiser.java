package nl.achan.ads.advertisers;

import static nl.achan.util.Configuration.ADVERTISER_REQUEST_QUEUE_DOUBLECLICK;

/**
 * Created by Etienne on 9-6-2017.
 */
public class DoubleClickAdvertiser extends BaseAdvertiser/*implements Advertiser, MessageListener*/{

    public DoubleClickAdvertiser() {
        super(ADVERTISER_REQUEST_QUEUE_DOUBLECLICK);
    }
}
