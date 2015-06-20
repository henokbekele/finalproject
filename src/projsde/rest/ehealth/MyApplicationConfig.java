package projsde.rest.ehealth;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("projectsdeone")
public class MyApplicationConfig extends ResourceConfig {
    public MyApplicationConfig () {
        packages("projsde.rest");
    }
}
