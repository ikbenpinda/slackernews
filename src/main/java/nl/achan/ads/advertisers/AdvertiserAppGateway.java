package nl.achan.ads.advertisers;

import nl.achan.ads.BidReply;
import nl.achan.ads.BidRequest;
import nl.achan.jms.MessageReceiverGateway;
import nl.achan.jms.MessageSenderGateway;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

import static nl.achan.monitoring.Configuration.ADVERTISER_REPLY_QUEUE;

/**
 * Created by Etienne on 9-6-2017.
 */
public class AdvertiserAppGateway implements MessageListener{

    private MessageSenderGateway sender;
    private MessageReceiverGateway receiver;
    private BidSerializer serializer;
    private Advertiser advertiser;

    public AdvertiserAppGateway(String requestChannel, Advertiser advertiser) {
        sender = new MessageSenderGateway(ADVERTISER_REPLY_QUEUE);
        receiver = new MessageReceiverGateway(requestChannel);
        receiver.setListener(this);
        serializer = new BidSerializer();
        this.advertiser = advertiser;
    }

    public void reply(BidReply reply){
        sender.send(sender.createTextMessage(serializer.toReply(reply)));
        Logger.getLogger(AdvertiserAppGateway.class.getName()).log(Level.INFO, "Replying with " + reply.toString());
    }

    private void onRequest(BidRequest bidRequest){
        advertiser.receiveRequest(bidRequest);
        Logger.getLogger(AdvertiserAppGateway.class.getName()).log(Level.INFO, "Received request: " + bidRequest.toString());
    }

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            onRequest(serializer.fromRequest(textMessage.getText()));
        } catch (JMSException e) {
            Logger.getLogger(AdvertiserAppGateway.class.getName()).log(Level.INFO, "Failed to parse message! :(");
            e.printStackTrace();
        }
    }
}
