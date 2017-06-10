package nl.achan.ads.advertisers;

import com.google.gson.Gson;
import nl.achan.ads.BidReply;
import nl.achan.ads.BidRequest;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

/**
 * Serializes and de-serializes bidding messages.
 * Encapsulated so additional mapping can be done from one place.
 *
 * Created by Etienne on 9-6-2017.
 */
public class BidSerializer {

    private Gson jsonSerializer = new Gson();

    public BidSerializer() {
    }

    public BidRequest fromRequest(String request){
        return jsonSerializer.fromJson(request, BidRequest.class);
    }

    public String toRequest(BidRequest request){
        return jsonSerializer.toJson(request);
    }

    public BidReply fromReply(String reply){
        return jsonSerializer.fromJson(reply, BidReply.class);
    }

    public String toReply(BidReply reply){
        return jsonSerializer.toJson(reply);
    }
}
