package ca.polymtl.metafy.resource;

import ca.polymtl.metafy.music.Database;
import ca.polymtl.metafy.music.dto.PlaylistSendDTO;
import ca.polymtl.metafy.music.dto.TrackSendDTO;
import ca.polymtl.metafy.music.dto.TracksSendDTO;
import ca.polymtl.metafy.streamerapi.IStreamerApi;
import ca.polymtl.metafy.streamerapi.spotify.SpotifyApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Singleton
@Path("/")
@Api(value = "api", description = "Core functionnalities")
public class MetafyResource {
    private static final Logger LOGGER = Logger.getLogger(MetafyResource.class.getName());
    //Temporary Database representing the model
    private static final Database database = new Database();

    private List<IStreamerApi> apis;

    static {
        LOGGER.setLevel(Level.ALL);
    }

    public MetafyResource() {
        super();
        apis = new ArrayList<>();
        apis.add(SpotifyApi.getInstance());
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
    public Response displaytest() { return Response.status(Response.Status.OK).entity(new PlaylistSendDTO()).build(); }

    @GET
    @Path("playlists/")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Playlists")
    public Response playlists() { return Response.status(Response.Status.OK).entity(database).build(); }

    @GET
    @Path("search/{query}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Search")
    /**
     * This Method is a HTTP GET request that returns a list of Tracks retrieved from all the services from the apis list.
     * The json returned is a serialisation of TracksSendDTO
     */
    public Response search(@PathParam("query") final String query) {
        return Response.status(Response.Status.OK)
                .entity(new TracksSendDTO(
                        apis.stream()
                            .map(api -> api.searchTrack(query))
                            .flatMap(List::stream)
                            .map(track -> new TrackSendDTO(track))
                            .collect(Collectors.toList())))
                .build();
    }

}
