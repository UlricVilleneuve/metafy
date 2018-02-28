package ca.polymtl.metafy.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@Path("/")
@Api(value = "api", description = "Core functionnalities")
public class MetafyResource {
    private static final Logger LOGGER = Logger.getLogger(MetafyResource.class.getName());

    static {
        LOGGER.setLevel(Level.ALL);
    }

    public MetafyResource() {
        super();
    }

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "Test")
    public Response test() {
        return Response.status(Response.Status.OK).entity("TestGET").build();
    }

}
