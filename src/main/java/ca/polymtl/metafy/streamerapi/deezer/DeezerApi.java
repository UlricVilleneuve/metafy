package ca.polymtl.metafy.streamerapi.deezer;

import ca.polymtl.metafy.music.Track;
import ca.polymtl.metafy.streamerapi.authentication.IAuthenticator;
import ca.polymtl.metafy.streamerapi.deezer.dto.DeezerTrackReturnDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Singleton to make HTTP requests to the Deezer API server
 * @author Theo Coulin
 */
public class DeezerApi {

    public static DeezerApi instance = null;

    private static final Logger LOGGER = Logger.getLogger(DeezerApi.class.getName());

    private IAuthenticator authenticator;
    private Client client;

    public DeezerApi() {
        client = ClientBuilder.newClient();
        // TODO : authenticator

    }

    /**
     * Get the SpotifyApi service instance. Instance is lazy initialized.
     * @return The SpotifyApi service instance
     */
    public static DeezerApi getInstance() {
        if(instance == null)
            instance = new DeezerApi();
        return instance;
    }

    public List<Track> searchTrack(String queryString) {
        WebTarget auth = client.target("https://connect.deezer.com/oauth/auth.php?app_id=274202&redirect_uri=http://localhost:8080/");
        auth.request();
        WebTarget resource = client.target("https://api.deezer.com/search").queryParam("q", queryString);
        DeezerTrackReturnDTO response = resource.request(MediaType.APPLICATION_JSON).get(DeezerTrackReturnDTO.class);
        System.out.println(response.toString());
        return Collections.emptyList();
    }

}
