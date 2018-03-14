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
import ca.polymtl.metafy.streamerapi.jamendo.dto.JamendoSearchRetrieveDTO;

public class JamendoApi implements IStreamerApi {

    private static JamendoApi instance = null;
    private static final Logger LOGGER = Logger.getLogger(JamendoApi.class.getName());

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
        WebTarget resource = client.target("https://api.jamendo.com/v3.0/tracks/?client_id=dacd3181&format=jsonpretty&limit=20&fullcount=true&search=" + queryString);

        Invocation.Builder request2 = resource.request();
        request2.accept(MediaType.APPLICATION_JSON);
        
        JamendoSearchRetrieveDTO response = request2.get(JamendoSearchRetrieveDTO.class);

        LOGGER.log(Level.INFO, "Queried \"" + queryString + "\" on Jamendo API, response was " + response);

        return response.getTracksReturnDTO().stream().map(track -> new Track(track.getTrackName(), track.getArtistName(), track.getTrackURL(), track.getTrackDuration(), "Jamendo")).collect(Collectors.toList());
    }
}