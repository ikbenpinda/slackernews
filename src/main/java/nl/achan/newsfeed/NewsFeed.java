package nl.achan.newsfeed;

import nl.achan.util.domain.Article;
import nl.achan.util.domain.ArticleView;

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
     * @param category - the category of the article.
     */
    void publishArticle(ArticleView article, String category);

}
