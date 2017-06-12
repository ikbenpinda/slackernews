package nl.achan.subscribers;

import nl.achan.util.domain.ArticleView;

/**
 * The basic listener representing an individual user.
 *
 * Created by Etienne on 9-6-2017.
 */
public class UserSubscriber implements Subscriber{

    private UserApplicationGateway gateway;
    private OnArticlePublishedCallback callback;

    public UserSubscriber() {
        this.gateway = new UserApplicationGateway();
    }

    public void subscribe(String topic, OnArticlePublishedCallback callback){
        this.callback = callback;
        gateway.subscribe(topic, article -> {
            if (callback != null)
                callback.execute(article);
        });
    }

    @Override
    public void subscribe(String topic) {
        subscribe(topic, null);
//        gateway.subscribe(topic, article -> {
//            // todo - handle incoming article.
//        });
    }

    public interface OnArticlePublishedCallback {
        void execute(ArticleView article);
    }
}
