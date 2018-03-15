package ca.polymtl.metafy;

import ca.polymtl.metafy.streamerapi.deezer.DeezerApi;
import ca.polymtl.metafy.streamerapi.spotify.SpotifyApi;

public class musicAPI {

    public static void main(String[] args) throws Exception {
        //SpotifyApi.getInstance().searchTrack("adele");
        DeezerApi.getInstance().searchTrack("i need a dollar");
    }

}
