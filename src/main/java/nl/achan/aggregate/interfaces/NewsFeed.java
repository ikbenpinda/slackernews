package nl.achan.aggregate.interfaces;

/**
 * Created by Etienne on 9-6-2017.
 */
public interface NewsFeed {

    Article parseArticle(Article article);
    void addToTopic(Article article);

}
