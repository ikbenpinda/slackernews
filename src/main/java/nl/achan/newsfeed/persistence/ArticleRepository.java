package nl.achan.newsfeed.persistence;

import nl.achan.util.domain.Article;

import java.util.List;

/**
 * Repository for articles.
 * This interface provides an abstraction over its implementation
 * so we can do this in-memory, through a database, whatever.
 *
 * Created by Etienne on 10-6-2017.
 */
public interface ArticleRepository {

    /**
     * Adds an article to the repository.
     */
    boolean add(Article article);

    /**
     * Removes an article from the repository.
     */
    boolean remove(long articleId);

    /**
     * Gets an article from the repository
     */
    Article find(long articleId);

    /**
     * Returns a list of all articles.
     */
    List<Article> findAll();
}
