package nl.achan.newsfeed.persistence;

import nl.achan.util.domain.Article;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple in-memory implementation for the articles repository.
 *
 * Created by Etienne on 10-6-2017.
 */
public class ArticleRepositoryInMemoryImpl implements ArticleRepository {

    List<Article> articles = new LinkedList<>();

    @Override
    public boolean add(Article article) {
        return articles.add(article);
    }

    @Override
    public boolean remove(long articleId) {
        return articles.remove(find(articleId));
    }

    @Override
    public Article find(long articleId) {
        for (Article article : articles){
            if (article.getArticleId() == articleId)
                return article;
        }
        return null;
    }

    @Override
    public List<Article> findAll() {
        return articles;
    }
}
