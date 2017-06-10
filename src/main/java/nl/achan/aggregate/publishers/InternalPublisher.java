package nl.achan.aggregate.publishers;

import nl.achan.aggregate.interfaces.Article;

/**
 * A publisher implementation for article submissions.
 *
 * Created by Etienne on 9-6-2017.
 */
public class InternalPublisher implements Publisher {

    PublisherApplicationGateway gateway;

    public InternalPublisher() {
        gateway = new PublisherApplicationGateway();
    }

    @Override
    public void publish(Article article) {
        gateway.publish(article);
    }
}
