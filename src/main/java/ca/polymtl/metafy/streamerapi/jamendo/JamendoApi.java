package ca.polymtl.metafy.streamerapi.jamendo;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import ca.polymtl.metafy.music.Track;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

import ca.polymtl.metafy.streamerapi.IStreamerApi;
import ca.polymtl.metafy.streamerapi.authentication.ApiKeyLoader;
import ca.polymtl.metafy.streamerapi.authentication.ApiKeyNotFoundException;
import ca.polymtl.metafy.streamerapi.jamendo.dto.JamendoSearchRetrieveDTO;

/**
 * This class is a Singleton used to make HTTP queries to the Spotify Web API.
 * @author lerongeur, wmouchere
 */
public class JamendoApi implements IStreamerApi {

    private static JamendoApi instance = null;
    private static final Logger LOGGER = Logger.getLogger(JamendoApi.class.getName());

    private static String apiKey;
    //retrieve API key from loader
    static {
        try {
            apiKey = ApiKeyLoader.getInstance().getApiKey("Jamendo");
        } catch (ApiKeyNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private Client client;

    private JamendoApi() {
        client = ClientBuilder.newClient();
    }

    /**
     * Get the Jamendo service instance. Instance is lazy initialized.
     * @return The JamendoApi service instance
     */
    public static JamendoApi getInstance() {
        if(instance == null)
            instance = new JamendoApi();
        return instance;
    }

    public List<Track> searchTrack(String queryString) {
        WebTarget resource = client.target("https://api.jamendo.com/v3.0/tracks")
                .queryParam("client_id", apiKey)
                .queryParam("format", "json")
                .queryParam("limit", "10")
                .queryParam("search", queryString);
        JamendoSearchRetrieveDTO response = resource.request(MediaType.APPLICATION_JSON)
                .get(JamendoSearchRetrieveDTO.class);

        LOGGER.log(Level.INFO, "Queried \"" + queryString + "\" on Jamendo API, response was " + response);

        return response.getTracksReturnDTO().stream()
                .map(track -> new Track(track.getTrackName(),
                        track.getArtistName(),
                        track.getTrackURL(),
                        track.getTrackDuration()*1000,
                        "Jamendo"))
                .collect(Collectors.toList());
    }
}