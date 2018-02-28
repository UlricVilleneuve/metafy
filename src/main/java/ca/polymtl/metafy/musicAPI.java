package ca.polymtl.metafy;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import ca.polymtl.metafy.streamerapi.authentication.SpotifyAuthenticator;
import ca.polymtl.metafy.streamerapi.authentication.dto.SpotifyAuthenticationReturnDTO;
import ca.polymtl.metafy.streamerapi.spotify.SpotifyApi;
import ca.polymtl.metafy.streamerapi.spotify.dto.SpotifySearchReturnDTO;

public class musicAPI {

    public static void main(String[] args) throws Exception {
        SpotifyApi.getInstance().searchTrack("adele");
    }

}
