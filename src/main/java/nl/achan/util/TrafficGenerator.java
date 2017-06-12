package nl.achan.util;

import nl.achan.ads.advertisers.AdMobAdvertiser;
import nl.achan.ads.advertisers.Advertiser;
import nl.achan.ads.advertisers.DoubleClickAdvertiser;
import nl.achan.ads.advertisers.PopAdsAdvertiser;
import nl.achan.util.domain.Article;
import nl.achan.newsfeed.NewsFeed;
import nl.achan.newsfeed.SlackerNews;
import nl.achan.publishers.InternalPublisher;
import nl.achan.publishers.Publisher;
import nl.achan.subscribers.Subscriber;
import nl.achan.subscribers.UserSubscriber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Generates traffic between the services.
 * The traffic handling will be logged by the server.
 * Another way would be to run independent instances,
 * but to keep things simple, everything happens internally.
 *
 * Created by Etienne on 9-6-2017.
 */
public class TrafficGenerator {

//    Advertiser AdMob;
//    Advertiser DoubleClick;
//    Advertiser PopAds;
//    Publisher ReutersSubmissions;
//    Publisher userSubmissions;
//    Publisher internalSubmissions;
//    Subscriber user1;
//    Subscriber user2;
//    Subscriber user3;
//
//    SlackerNews service;

    // handle generation strategies and artificial event triggers.


    /**
     * Simulates the full stack.
     * Where the individual *CLI classes are responsible for one interface,
     * this will run the publishers, subscribers, advertisers, and central service.
     */
    public static void main(String[] args) throws IOException {

        String input;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Initializing full stack...");

        // todo - Clear the JMS pipeline.

        System.out.println("Initializing core system...[1/4]");

        // The core system logs everything, but due to usage of Logger.log()
        // instead of System.out, not everything might show up in the console.
        NewsFeed newsFeed = new SlackerNews();

        System.out.println("Initializing advertisers...[2/4]");

        // These will automatically receive and reply to incoming requests.
        // The only difference between instances is the
        // potentially different bidding process.
        Advertiser advertiser1 = new AdMobAdvertiser();
        Advertiser advertiser2 = new DoubleClickAdvertiser();
        Advertiser advertiser3 = new PopAdsAdvertiser();

        System.out.println("Initializing subscribers...[3/4]");

        // Fire up different subscribers to demonstrate the use of topics.
        // Everything is callback based - fire-and-forget.
        Subscriber subscriber1 = new UserSubscriber();
        Subscriber subscriber2 = new UserSubscriber();
        Subscriber subscriber3 = new UserSubscriber();
        subscriber1.subscribe(Categories.TECHNOLOGY.name()+"Topic", article -> {
            System.out.println("Subscriber #1: New article published in category " + "" + ": " + article.toString());
        });
        subscriber2.subscribe(Categories.TECHNOLOGY.name()+"Topic", article -> {
            System.out.println("Subscriber #2: New article published in category " + "" + ": " + article.toString());
        });
        subscriber3.subscribe(Categories.GAMING.name()+"Topic", article -> {
            System.out.println("Subscriber #3: New article published in category " + "" + ": " + article.toString());
        });

        System.out.println("Setting up publishers...[4/4]");

        // A publisher basically works as a proxy,
        // so there is no need to use multiple instances.
        Publisher publisher = new InternalPublisher();

        Article article1 = new Article(1, "http://example.com/articles/1", "Amonas", Categories.MISCELLANEOUS);
        Article article2 = new Article(2, "http://example.com/articles/2", "FeedBuzz", Categories.POLITICS);
        Article article3 = new Article(3, "http://example.com/articles/3", "MediumRare", Categories.TECHNOLOGY);
        Article article4 = new Article(4, "http://example.com/articles/4", "MediumRare", Categories.TECHNOLOGY);
        Article article5 = new Article(5, "http://example.com/articles/5", "Amonas", Categories.TECHNOLOGY);
        Article article6 = new Article(6, "http://example.com/articles/6", "Leddit", Categories.GAMING);
        Article article7 = new Article(7, "http://example.com/articles/7", "Leddit", Categories.GAMING);
        Article article8 = new Article(8, "http://example.com/articles/8", "Leddit", Categories.GAMING);
        List<Article> articles = new ArrayList<>(Arrays.asList(article1, article2, article3, article4, article5, article6, article7, article8));

        // todo - Display REST API.

        System.out.println("Done!");
        System.out.println("Publish articles(Y = all, S = Single-only)? [Y/S/N?]");
        input = in.readLine();
        if (input.toUpperCase().equals("Y"))
            for (Article article : articles) {
                System.out.println("Publishing article (" + article.toString() + ")");
                publisher.publish(article);
            }
        else if (input.toUpperCase().equals("S")) {
            System.out.println("Article ID?");
            for (Article article : articles) {
                System.out.println(article.toString());
            }
            int id = Integer.parseInt(in.readLine());
            publisher.publish(articles.get(id - 1));
        }

        // Workaround to move additional printing to bottom.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
        System.out.println("Press enter to exit.");
        in.readLine();
    }
}
