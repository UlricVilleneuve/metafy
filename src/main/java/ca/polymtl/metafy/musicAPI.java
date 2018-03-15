package ca.polymtl.metafy;

import ca.polymtl.metafy.streamerapi.spotify.SpotifyApi;

public class musicAPI {

    public static void main(String[] args) throws Exception {
        SpotifyApi.getInstance().searchTrack("adele");
    }

}
