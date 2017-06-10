package nl.achan.aggregate.subscribers;

/**
 * The basic listener representing an individual user.
 *
 * Created by Etienne on 9-6-2017.
 */
public class UserSubscriber implements Subscriber{

    UserApplicationGateway gateway;

    public UserSubscriber(UserApplicationGateway gateway) {
        this.gateway = new UserApplicationGateway();
    }

    @Override
    public void subscribe(String topic) {
        gateway.subscribe(topic, article -> {
            // todo - handle incoming article.
        });
    }
}
