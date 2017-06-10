package nl.achan.aggregate.interfaces;

/**
 * Canonical data model for any articles submitted.
 *
 * Created by Etienne on 9-6-2017.
 */
public interface Article {
    long getArticleId();
    String getPublisherName();
    String getCategory();
}
