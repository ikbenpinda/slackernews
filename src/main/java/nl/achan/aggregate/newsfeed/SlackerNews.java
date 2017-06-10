package nl.achan.aggregate.newsfeed;

import nl.achan.ads.AdBrokerImpl;
import nl.achan.aggregate.interfaces.Article;
import nl.achan.aggregate.interfaces.ArticleView;
import nl.achan.persistence.ArticleRepository;
import nl.achan.persistence.ArticleRepositoryInMemoryImpl;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Heart of the application, responsible for processing article submissions,
 * finding ads to display, and publishing them to the consumers.
 *
 * Created by Etienne on 9-6-2017.
 */
public class SlackerNews implements NewsFeed {

    private NewsFeedAppGateway gateway;
    private AdBroker broker;
    private ArticleRepository repository;// todo - some sort of topic cache for the rest api

    private Logger logger;

    public SlackerNews() {
        this.gateway = new NewsFeedAppGateway();
        this.broker = new AdBrokerImpl();
        this.repository = new ArticleRepositoryInMemoryImpl();

        gateway.setOnArticleSubmissionCallback(article -> {
            receiveSubmission(article);
        });
        logger = Logger.getLogger(SlackerNews.class.getName());
    }

    // todo - add to interface?
    public void receiveSubmission(Article submission){
        logger.log(Level.INFO, "Received submission!");
        injectAdvertisements(submission);
    }

    @Override
    public void injectAdvertisements(Article article) {
        broker.findAdvertiserForArticle(article, adLink -> {
            publishArticle(prepare(article, adLink));
        });
    }

    @Override
    public void publishArticle(ArticleView article) {
        gateway.publishArticle(article);
    }

    /**
     * Push the article to the repository of articles so it can be retrieved later.
     * @param article the original article.
     */
    // todo - add to interface?
    public void updateRepository(Article article){
        repository.add(article);
    }

    /**
     * Prepares the article for publication to a topic.
     * @param article the original article.
     * @param adLink the resolved advertisement for this article.
     * @return an ArticleView ready for publication.
     */
    private ArticleView prepare(Article article, String adLink){
        updateRepository(article);
        return new ArticleView(article.getArticleId(), article.getArticleLink(), adLink);
    }
}
