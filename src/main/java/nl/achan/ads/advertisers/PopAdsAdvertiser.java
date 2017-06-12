package nl.achan.ads.advertisers;

import static nl.achan.util.Configuration.ADVERTISER_REQUEST_QUEUE_POPADS;

/**
 * Created by Etienne on 9-6-2017.
 */
public class PopAdsAdvertiser extends BaseAdvertiser/*implements Advertiser, MessageListener*/{

    public PopAdsAdvertiser() {
        super(ADVERTISER_REQUEST_QUEUE_POPADS);
    }
}
