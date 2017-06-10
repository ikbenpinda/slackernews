package nl.achan.aggregate.newsfeed;

import nl.achan.aggregate.interfaces.Article;
import nl.achan.aggregate.interfaces.ArticleView;

/**
 * Created by Etienne on 9-6-2017.
 */
public interface NewsFeed {

    /**
     * Process any new submissions.
     * @param article the unprocessed article.
     */
    void injectAdvertisements(Article article);

    /**
     * Make the prepared article available for its users.
     * @param article the processed article (includes ads).
     */
    void publishArticle(ArticleView article);

}
