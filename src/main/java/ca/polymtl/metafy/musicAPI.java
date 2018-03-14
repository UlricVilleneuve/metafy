package ca.polymtl.metafy;

import ca.polymtl.metafy.streamerapi.jamendo.JamendoApi;

public class musicAPI {

    public static void main(String[] args) throws Exception {

        JamendoApi.getInstance().searchTrack("adele");
        JamendoApi.getInstance().searchTrack("Rolling in the deep");
    }

}
