package nl.achan.aggregate.subscribers;

/**
 * Created by Etienne on 9-6-2017.
 */
public interface Subscriber {
    void subscribe(String topic);
    void subscribe(String topic, UserSubscriber.OnArticlePublishedCallback callback);
}
