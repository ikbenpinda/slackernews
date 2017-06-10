package nl.achan.api;

import nl.achan.ads.advertisers.AdvertiserAppGateway;
import nl.achan.aggregate.NewsFeedAppGateway;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * REST API for all topic-related stuff.
 *
 * Created by Etienne on 9-6-2017.
 */
@Path("topics")
public class TopicsAPI {

    @Inject
    NewsFeedAppGateway gateway;

    /**
     * Returns the five latest articles for the given topic.
     * @return
     */
    @GET
    @Path("{topic}/latest")
    public Response getLatestFromTopic(){
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }

    /**
     * Returns all articles for the given topic.
     * @return
     */
    @GET
    @Path("{topic}/all")
    public Response getAllFromTopic(){
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }

    /**
     * Returns a list of all topics.
     * @return
     */
    @GET
    @Path("topics")
    public Response listTopics(){
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }

    /**
     * Places a new subscription on the specific topic.
     * @return
     */
    @POST
    @Path("{topic}")
    public Response createTopic(){
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }
}
