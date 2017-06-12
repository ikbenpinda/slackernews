package nl.achan.newsfeed;

import nl.achan.util.domain.Article;
import nl.achan.util.domain.ArticleView;
import nl.achan.newsfeed.adbroker.AdBroker;
import nl.achan.newsfeed.adbroker.AdBrokerImpl;
import nl.achan.newsfeed.persistence.ArticleRepository;
import nl.achan.newsfeed.persistence.ArticleRepositoryInMemoryImpl;

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
    private ArticleRepository repository;

    private Logger logger;

    public SlackerNews() {
        gateway = new NewsFeedAppGateway();
        broker = new AdBrokerImpl();
        repository = new ArticleRepositoryInMemoryImpl();
        logger = Logger.getLogger(SlackerNews.class.getName());

        gateway.setOnArticleSubmissionCallback(this::receiveSubmission);
    }

    // todo - add to interface?
    public void receiveSubmission(Article submission){
        logger.log(Level.INFO, "Received submission!");
        updateRepository(submission);
        injectAdvertisements(submission);
    }

    @Override
    public void injectAdvertisements(Article article) {
        broker.findAdvertiserForArticle(article, adLink -> {
            logger.log(Level.INFO, "Resolved adspace, publishing...");
            publishArticle(prepare(article, adLink), article.getCategory());
        });
    }

    @Override
    public void publishArticle(ArticleView article, String topic) {
        gateway.publishArticle(article, topic);
        logger.log(Level.INFO, "Published article!");
        logger.log(Level.FINE, "Published ArticleView: " + article.toString());
    }

    /**
     * Push the article to the repository of articles so it can be retrieved later.
     * @param article the original article.
     */
    // todo - add to interface?
    // note - moving this up the ladder allows publishing through the API.
    private void updateRepository(Article article){
        repository.add(article);
        logger.log(Level.INFO, "Saved article to repository.");
    }

    /**
     * Prepares the article for publication to a topic.
     * @param article the original article.
     * @param adLink the resolved advertisement for this article.
     * @return an ArticleView ready for publication.
     */
    private ArticleView prepare(Article article, String adLink){
        logger.log(Level.INFO, "Preparing article for publication.");
        logger.log(Level.INFO, "Article prepared!");
        ArticleView view = new ArticleView(article.getArticleId(), article.getArticleLink(), adLink);
        logger.log(Level.FINE, "Original article was " + article.toString() + "\n with adLink: " + adLink + ", resulting in view: " + view.toString());
        return view;
    }
}
