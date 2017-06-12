package nl.achan.newsfeed.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Starting point for the REST API.
 * Any additional dependencies to be used for the REST framework can be added here.
 *
 * Created by Etienne on 9-6-2017.
 */
@ApplicationPath("api")
public class APIConfig extends Application{
    public APIConfig() {
    }
}
