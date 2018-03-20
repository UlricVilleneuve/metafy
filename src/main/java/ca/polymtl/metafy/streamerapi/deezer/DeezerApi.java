package ca.polymtl.metafy.streamerapi.deezer;

import ca.polymtl.metafy.music.Track;
import ca.polymtl.metafy.streamerapi.IStreamerApi;
import ca.polymtl.metafy.streamerapi.authentication.ApiKeyLoader;
import ca.polymtl.metafy.streamerapi.authentication.ApiKeyNotFoundException;
import ca.polymtl.metafy.streamerapi.deezer.dto.DeezerSearchReturnDTO;
import ca.polymtl.metafy.streamerapi.jamendo.dto.JamendoSearchRetrieveDTO;
import org.hibernate.boot.model.source.spi.IdentifierSource;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


/**
 * Singleton to make HTTP requests to the Spotify API server
 * @author Theo Coulin, wmouchere
 */
public class DeezerApi implements IStreamerApi {

    public static DeezerApi instance = null;

    private static final Logger LOGGER = Logger.getLogger(DeezerApi.class.getName());

    private Client client;

    private static String apiKey;
    //retrieve API key from loader
    static {
        try {
            apiKey = ApiKeyLoader.getInstance().getApiKey("Deezer");
        } catch (ApiKeyNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public DeezerApi() {
        client = ClientBuilder.newClient();
    }

    /**
     * Get the DeezerApi service instance. Instance is lazy initialized.
     * @return The DeezerApi service instance
     */
    public static DeezerApi getInstance() {
        if(instance == null)
            instance = new DeezerApi();
        return instance;
    }

    public List<Track> searchTrack(String queryString) {
        WebTarget resource = client.target("https://api.deezer.com/search/track")
                .queryParam("limit", "10")
                .queryParam("q", queryString);
        DeezerSearchReturnDTO response = resource.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .get(DeezerSearchReturnDTO.class);

        LOGGER.log(Level.INFO, "Queried \"" + queryString + "\" on Deezer API, response was " + response);

        return response.getItems().stream()
                .map(track -> new Track(track.getTitle(),
                        track.getArtist().getName(),
                        track.getTrackURL(),
                        track.getDuration()*1000,
                        "Deezer"))
                .collect(Collectors.toList());
    }

}
