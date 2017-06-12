package nl.achan.newsfeed.api;

import nl.achan.newsfeed.NewsFeed;
import nl.achan.newsfeed.NewsFeedAppGateway;
import nl.achan.newsfeed.SlackerNews;
import nl.achan.util.Categories;
import nl.achan.util.domain.Article;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * REST API for all topic-related stuff.
 *
 * Created by Etienne on 9-6-2017.
 */
@Path("topics")
public class TopicsAPI {

    @Inject
    SlackerNews slackerNews;

    /**
     * Returns the five latest articles for the given topic.
     * @return
     */
    @GET
    @Path("{topic}/latest")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLatestFromTopic(@PathParam("topic") String topic){
        List<Article> articles = slackerNews.getLatestArticles(topic);
        if (articles != null)
            return Response.ok(articles, MediaType.APPLICATION_JSON).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    /**
     * Returns all articles for the given topic.
     * @return
     */
    @GET
    @Path("{topic}/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFromTopic(@PathParam("topic") String topic){
        List<Article> articles = slackerNews.getAllFromTopic(topic);
        if (articles != null)
            return Response.ok(articles, MediaType.APPLICATION_JSON).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    /**
     * Returns a list of all topics.
     * @return
     */
    @GET
    @Path("topics")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTopics(){
        return Response.ok(Categories.values(), MediaType.APPLICATION_JSON).build();
    }

    /**
     * Places a new subscription on the specific topic.
     *
     * Currently not implemented because the implementation likely requires websockets to really work,
     * which is currently out-of-scope.
     */
    @POST
    @Path("{topic}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTopic(){
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }
}
