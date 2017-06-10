package nl.achan.aggregate;

import nl.achan.aggregate.interfaces.Article;
import nl.achan.aggregate.interfaces.NewsFeed;

/**
 * Heart of the application, responsible for processing article submissions,
 * finding ads to display, and publishing them to the consumers.
 *
 * Created by Etienne on 9-6-2017.
 */
public class SlackerNews implements NewsFeed {

    NewsFeedAppGateway gateway;
    // todo - some sort of topic cache for the rest api

    public SlackerNews() {
        this.gateway = new NewsFeedAppGateway(
            bid -> {
                // todo - Transform article message to include ad
                // todo - Send article;
            },
            article -> {
                // todo - Request bids for article
                // todo - Parse return and publish
            });
    }

    @Override
    public Article parseArticle(Article article) {
        gateway.requestBids(article);
        return null;
    }

    @Override
    public void addToTopic(Article article) {
        gateway.publishArticle();
    }
}
