package ca.polymtl.metafy.streamerapi.deezer;

import ca.polymtl.metafy.music.Track;
import ca.polymtl.metafy.streamerapi.authentication.DeezerAuthenticator;
import ca.polymtl.metafy.streamerapi.authentication.IAuthenticator;
import ca.polymtl.metafy.streamerapi.deezer.dto.DeezerAuthReturnDTO;
import ca.polymtl.metafy.streamerapi.deezer.dto.DeezerCodeReturnDTO;
import ca.polymtl.metafy.streamerapi.deezer.dto.DeezerSearchReturnDTO;
import ca.polymtl.metafy.streamerapi.deezer.dto.DeezerTrackReturnDTO;
import org.glassfish.jersey.server.Uri;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.awt.*;
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

    private static final String APP_ID = "274202";
    private static final String APP_SECRET = "a0d697805fbf3b64ce35dd87d106a797";
    // /!\ Security
    private static final String token = "nyYTHKeiAiWewMelw0h3Fk2puSMU2IhNkYGRhRgVo2PWHRukmv";

    public DeezerApi() {
        client = ClientBuilder.newClient();
        authenticator = DeezerAuthenticator.getInstance();


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
        //Log in and get token : now with an tokn with no expiration date
        /*WebTarget auth1 = client.target("https://connect.deezer.com/oauth/auth.php?app_id="+APP_ID+"&redirect_uri=http://localhost:8080&perms=basic_access,offline_access");
        DeezerCodeReturnDTO code = auth1.request(MediaType.APPLICATION_JSON).get(DeezerCodeReturnDTO.class);
        System.out.println(code.getDeezerCode());
        WebTarget auth2 = client.target("https://connect.deezer.com/oauth/access_token.php?app_id="+APP_ID+"&secret="+APP_SECRET+"&code="+code.getDeezerCode()+"response_type=token");
        DeezerAuthReturnDTO deezerAuthReturnDTO = auth2.request(MediaType.APPLICATION_JSON).get(DeezerAuthReturnDTO.class);*/


        WebTarget resource = client.target("https://api.deezer.com/search?q=track:"+queryString+"&limit=10");//.queryParam("q", queryString);

        DeezerSearchReturnDTO response = resource.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .get(DeezerSearchReturnDTO.class);
        System.out.println(response.toString());
        return Collections.emptyList();
    }

}
