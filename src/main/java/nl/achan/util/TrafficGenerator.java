package nl.achan.util;

import nl.achan.ads.Advertiser;
import nl.achan.aggregate.SlackerNews;
import nl.achan.aggregate.publishers.Publisher;
import nl.achan.aggregate.subscribers.Subscriber;

/**
 * Generates traffic between the services.
 * The traffic handling will be logged by the server.
 * Another way would be to run independent instances,
 * but to keep things simple, everything happens internally.
 *
 * Created by Etienne on 9-6-2017.
 */
public class TrafficGenerator {

    Advertiser AdMob;
    Advertiser DoubleClick;
    Advertiser PopAds;
    Publisher ReutersSubmissions;
    Publisher userSubmissions;
    Publisher internalSubmissions;
    Subscriber user1;
    Subscriber user2;
    Subscriber user3;

    SlackerNews service;

    // handle generation strategies and artificial event triggers.
}
