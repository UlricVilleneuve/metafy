package ca.polymtl.metafy.resource;

import ca.polymtl.metafy.music.Playlist;
import ca.polymtl.metafy.music.Track;
import ca.polymtl.metafy.music.dto.PlaylistDTO;
import ca.polymtl.metafy.music.dto.TrackDTO;
import ca.polymtl.metafy.streamerapi.IStreamerApi;
import ca.polymtl.metafy.streamerapi.deezer.DeezerApi;
import ca.polymtl.metafy.streamerapi.jamendo.JamendoApi;
import ca.polymtl.metafy.streamerapi.spotify.SpotifyApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@Path("/")
@Api(value = "api", description = "Core functionnalities")
public class MetafyResource {

    private static final Logger LOGGER = Logger.getLogger(MetafyResource.class.getName());

    private final EntityManagerFactory emf;
    private final EntityManager em;

    private List<IStreamerApi> apis;

    static {
        LOGGER.setLevel(Level.ALL);
    }

    public MetafyResource() {
        super();
        apis = new ArrayList<>();
        apis.add(SpotifyApi.getInstance());
        apis.add(DeezerApi.getInstance());
        apis.add(JamendoApi.getInstance());

        emf = Persistence.createEntityManagerFactory("metafy");
        em = emf.createEntityManager();
    }

    /**
     * Cleans the connection with the database before system shutdown.
     */
    public void flush() {
        em.clear();
        em.close();
        emf.close();
    }

    @Override
    protected void finalize() throws Throwable {
        flush();
        super.finalize();
    }

    @POST
    @Path("playlist/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * This method is a HTTP POST request that creates and saves a new playlist in the system.
     * It expects a json only containing the field "name" of PlaylistDTO
     * The json returned is a serialisation of the newly created playlist
     */
    public Response newPlaylist(final PlaylistDTO playlistDto) {
        final EntityTransaction tr = em.getTransaction();
        try {
            Playlist playlist = new Playlist(playlistDto.getName());
            tr.begin();
            em.persist(playlist);
            tr.commit();
            LOGGER.log(Level.INFO, "Added new playlist " + playlist);
            return Response.status(Response.Status.OK).entity(new PlaylistDTO(playlist)).build();
        } catch(final Throwable ex) {
            if(tr.isActive()) {
                tr.rollback();
            }
            LOGGER.log(Level.SEVERE, "Crash on adding a new playlist " + playlistDto, ex);
            throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_BAD_REQUEST).build());
        }
    }

    @GET
    @Path("playlist/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * This method is a HTTP GET request that returns the playlist that has the id passed as a query parameter.
     * The json return is a serialization of a PlaylistDTO
     */
    public Response getPlaylistById(@PathParam("id") final int id) {
        try {
            final Playlist playlist = em.createNamedQuery("Playlist.getById",Playlist.class).setParameter("id", id).getSingleResult();
            LOGGER.log(Level.INFO, "Get playlist with id " + id + ",got " + playlist);
            return Response.status(Response.Status.OK).entity(new PlaylistDTO(playlist)).build();
        } catch (final Throwable ex) {
            LOGGER.log(Level.SEVERE, "Crash on finding playlist with id " + id, ex);
            throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_BAD_REQUEST).build());
        }
    }

    @DELETE
    @Path("playlist/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * This method is a HTTP DELETE request that removes the playlist that has the id passed as a query parameter.
     * The json return is a serialization of a PlaylistDTO
     */
    public Response removePlaylist(@PathParam("id") final int id) {
        final EntityTransaction tr = em.getTransaction();
        try {
            final Playlist playlist = em.createNamedQuery("Playlist.getById",Playlist.class).setParameter("id", id).getSingleResult();
            tr.begin();
            //playlist.getTracks().forEach(track -> em.remove(track));
            em.remove(playlist);
            tr.commit();
            LOGGER.log(Level.INFO, "Sucessfully removed playlist with id " + id);
            return Response.status(Response.Status.OK).entity(new PlaylistDTO(playlist)).build();
        } catch (final Throwable ex) {
            if(tr.isActive()) {
                tr.rollback();
            }
            LOGGER.log(Level.SEVERE, "Crash on removing playlist with id " + id, ex);
            throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_BAD_REQUEST).build());
        }
    }

    @GET
    @Path("playlists/")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * This method is a HTTP GET request that returns a list of all Playlists saved in the system.
     * The json return is a serialization of an array of PlaylistDTO
     */
    public Response getPlaylists() {
        try {
            final List<Playlist> playlists = em.createNamedQuery("Playlist.getAll", Playlist.class).getResultList();
            LOGGER.log(Level.INFO, "Get all playlists " + playlists);
            return Response.status(Response.Status.OK).entity(playlists.stream()
                        .map(playlist -> new PlaylistDTO(playlist))
                        .toArray(PlaylistDTO[]::new))
                    .build();
        } catch (final Throwable ex) {
            LOGGER.log(Level.SEVERE, "Crash on finding all playlists", ex);
            throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_BAD_REQUEST).build());
        }
    }

    @PUT
    @Path("playlist/{id}/add/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * This method is a HTTP PUT request that adds a Track to the playlist of the given id.
     * The json returned is the updated playlist.
     */
    public Response addTrackToPlaylist(@PathParam("id") final int id, final TrackDTO trackDTO) {
        final EntityTransaction tr = em.getTransaction();
        try {
            final Playlist playlist = em.createNamedQuery("Playlist.getById",Playlist.class).setParameter("id", id).getSingleResult();
            Track track = new Track(trackDTO.getName(), trackDTO.getAuthor(), trackDTO.getUrl(), trackDTO.getDuration(), trackDTO.getOrigin());
            tr.begin();
            em.persist(track);
            playlist.addTrack(track);
            tr.commit();
            LOGGER.log(Level.INFO, "Added track " + track + " to playlist " + playlist);
            return Response.status(Response.Status.OK).entity(new PlaylistDTO(playlist)).build();
        } catch (final Throwable ex) {
            if(tr.isActive()) {
                tr.rollback();
            }
            LOGGER.log(Level.SEVERE, "Crash on adding track " + trackDTO + " to playlist with id " + id, ex);
            throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_BAD_REQUEST).build());
        }
    }

    @GET
    @Path("search/{query}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Search")
    /**
     * This Method is a HTTP GET request that returns a list of Tracks retrieved from all the services from the apis list.
     * The json returned is a serialisation of an array of TrackDTO
     */
    public Response search(@PathParam("query") final String query) {
        return Response.status(Response.Status.OK)
                .entity(apis.stream()
                            .map(api -> api.searchTrack(query))
                            .flatMap(List::stream)
                            .map(track -> new TrackDTO(track))
                            .toArray(TrackDTO[]::new))
                .build();
    }

}
