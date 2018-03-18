package ca.polymtl.metafy;

import ca.polymtl.metafy.streamerapi.deezer.DeezerApi;
import ca.polymtl.metafy.streamerapi.spotify.SpotifyApi;
import ca.polymtl.metafy.streamerapi.jamendo.JamendoApi;

public class musicAPI {

    public static void main(String[] args) throws Exception {
        SpotifyApi.getInstance().searchTrack("adele");
        JamendoApi.getInstance().searchTrack("adele");
        JamendoApi.getInstance().searchTrack("Rolling in the deep");
        DeezerApi.getInstance().searchTrack("adele");
    }

}
