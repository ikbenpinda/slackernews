package nl.achan.monitoring;

import nl.achan.aggregate.Categories;

/**
 * A collection of hardcoded key values.
 *
 * Created by Etienne on 9-6-2017.
 */
public class Configuration { // FIXME - move to properties file?

    /**
     * The ActiveMQ port. The default port is 61616.
     */
    public static final int ACTIVEMQ_PORT = 32768;

    /**
     * The IP-address for the docker machine. Default address = 192.168.99.100.
     */
    public static final String DOCKER_IP = "192.168.99.100";

    /**
     * The full broker URL for the ActiveMQConnectionFactory.
     */
    public static final String DEFAULT_BROKER_URL = "tcp://" + DOCKER_IP + ":" + ACTIVEMQ_PORT;

    /**
     * The queue for publishers to publish articles to.
     */
    public static final String PUBLISHER_QUEUE = "PublisherQueue";

    /**
     * Channel name for the related topic.
     */
    public static final String SUBSCRIBER_TOPIC_TECHNOLOGY = Categories.TECHNOLOGY.name() + "Topic";

    /**
     * Channel name for the related topic.
     */
    public static final String SUBSCRIBER_TOPIC_SPORTS = Categories.SPORTS.name() + "Topic";

    /**
     * Channel name for the related topic.
     */
    public static final String SUBSCRIBER_TOPIC_GAMING = Categories.GAMING.name() + "Topic";

    /**
     * Channel name for the related topic.
     */
    public static final String SUBSCRIBER_TOPIC_MISCELLANEOUS = Categories.MISCELLANEOUS.name() + "Topic";

    /**
     * Channel name for the related topic.
     */
    public static final String SUBSCRIBER_TOPIC_POLITICS = Categories.POLITICS.name() + "Topic";

    /**
     * The queue for the AdMob bid replies.
     */
    public static final String ADVERTISER_REPLY_QUEUE_ADMOB = "AdMobReplyQueue";

    /**
     * The queue for the article adspace bidding by AdMob.
     */
    public static final String ADVERTISER_REQUEST_QUEUE_ADMOB = "AdMobRequestQueue";

    /**
     * The queue for the DoubleClick bid replies.
     */
    public static final String ADVERTISER_REPLY_QUEUE_DOUBLECLICK = "DoubleClickReplyQueue";

    /**
     * The queue for the article adspace bidding by DoubleClick.
     */
    public static final String ADVERTISER_REQUEST_QUEUE_DOUBLECLICK = "DoubleClickRequestQueue";

    /**
     * The queue for the PopAds bid replies.
     */
    public static final String ADVERTISER_REPLY_QUEUE_POPADS = "PopAdsReplyQueue";

    /**
     * The queue for the article adspace bidding by PopAds.
     */
    public static final String ADVERTISER_REQUEST_QUEUE_POPADS = "PopAdsRequestQueue";

}
