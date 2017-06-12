package nl.achan.newsfeed;

import nl.achan.util.Categories;
import nl.achan.util.domain.Article;
import nl.achan.util.domain.ArticleView;
import nl.achan.newsfeed.adbroker.AdBroker;
import nl.achan.newsfeed.adbroker.AdBrokerImpl;
import nl.achan.newsfeed.persistence.ArticleRepository;
import nl.achan.newsfeed.persistence.ArticleRepositoryInMemoryImpl;

import java.awt.peer.ChoicePeer;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Heart of the application, responsible for processing article submissions,
 * finding ads to display, and publishing them to the consumers.
 *
 * Created by Etienne on 9-6-2017.
 */
public class SlackerNews implements NewsFeed {

    private static final int LATEST_ARTICLES_QUERY_LIMIT = 5;

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

    // todo - move to AppGateway, check parseTopic, deploy to check API.

    /**
     * Gets the latest articles from the repository.
     * The amount of articles is configured in LATEST_ARTICLES_QUERY_LIMIT.
     * @param topic the topic to search by.
     * @return a list of articles, or null if the topic was invalid.
     */
    public List<Article> getLatestArticles(String topic) {
        if (!isValidTopic(topic))
            return null;
        
        List<Article> filtered = new LinkedList<>();
        List<Article> articles = repository.findAll();
        for (int i = 0; i < LATEST_ARTICLES_QUERY_LIMIT && i < articles.size(); i++)
            filtered.add(articles.get(i));
        return filtered;
    }

    /**
     * Gets all articles for a given topic.
     * @param topic the topic to search by.
     * @return a list of articles, or null if the topic was invalid.
     */
    public List<Article> getAllFromTopic(String topic) {
        if (!isValidTopic(topic))
            return null;

        List<Article> relatedArticles = new LinkedList<>();
        for (Article article : repository.findAll()) {
            if (article.getCategory().equals(topic))
                relatedArticles.add(article);
        }
        return relatedArticles;
    }

    /**
     * Checks the topic given and tries to make sense of it.
     * @return true if it is a valid topic. If not: false.
     */
    public boolean isValidTopic(String topic){
        for (Categories category : Categories.values()){
            if (category.name().equals(topic))
                return true;
        }
        return false;
    }
}
