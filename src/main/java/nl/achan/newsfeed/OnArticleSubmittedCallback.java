package nl.achan.newsfeed;

import nl.achan.util.domain.Article;

/**
 * Created by Etienne on 9-6-2017.
 */
public interface OnArticleSubmittedCallback {
    void execute(Article article);
}
