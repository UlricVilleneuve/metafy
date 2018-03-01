package ca.polymtl.metafy.resource;

import ca.polymtl.metafy.music.Database;
import ca.polymtl.metafy.music.Playlist;
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
    //Temporary Database representing the model
    private static final Database database = new Database();

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

    @GET
    @Path("displaytest/")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Test")
    public Response displaytest() { return Response.status(Response.Status.OK).entity(new Playlist()).build(); }

    @GET
    @Path("playlists/")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Playlists")
    public Response playlists() { return Response.status(Response.Status.OK).entity(database).build(); }

}
