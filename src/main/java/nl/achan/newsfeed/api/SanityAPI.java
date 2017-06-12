package nl.achan.newsfeed.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Sanity check for checking the REST API state.
 *
 * Created by Etienne on 9-6-2017.
 */
@Path("test")
public class SanityAPI {

    @GET
    @Path("check")
    public Response sayHi(){
        return Response.ok("hi.").build();
    }
}
