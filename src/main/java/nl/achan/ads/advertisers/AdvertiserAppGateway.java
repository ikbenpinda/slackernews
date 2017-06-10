package nl.achan.ads.advertisers;

import nl.achan.ads.Advertiser;
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

/**
 * Created by Etienne on 9-6-2017.
 */
public class AdvertiserAppGateway implements MessageListener{

    private MessageSenderGateway sender;
    private MessageReceiverGateway receiver;
    private BidSerializer serializer;
    private Advertiser advertiser;

    public AdvertiserAppGateway(final String replyChannel, final String requestChannel, Advertiser advertiser) {
        sender = new MessageSenderGateway(replyChannel);
        receiver = new MessageReceiverGateway(requestChannel);
        receiver.setListener(this);
        serializer = new BidSerializer();
        this.advertiser = advertiser;
    }

    public void reply(BidReply reply){
        sender.createTextMessage(serializer.toReply(reply));
        Logger.getLogger(AdvertiserAppGateway.class.getName()).log(Level.INFO, "Replying with " + reply.toString());
    }

    public void onRequest(String request){
        BidRequest bidRequest = serializer.fromRequest(request);
        advertiser.receiveRequest(bidRequest);
        Logger.getLogger(AdvertiserAppGateway.class.getName()).log(Level.INFO, "Received request: " + bidRequest.toString());
    }

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            onRequest(textMessage.getText());
        } catch (JMSException e) {
            Logger.getLogger(AdvertiserAppGateway.class.getName()).log(Level.INFO, "Failed to parse message! :(");
            e.printStackTrace();
        }
    }
}