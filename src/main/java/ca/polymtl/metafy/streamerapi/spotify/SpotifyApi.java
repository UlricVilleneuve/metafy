package ca.polymtl.metafy.streamerapi.spotify;

import ca.polymtl.metafy.streamerapi.IStreamerApi;
import ca.polymtl.metafy.streamerapi.authentication.SpotifyAuthenticator;
import ca.polymtl.metafy.streamerapi.spotify.dto.SpotifySearchReturnDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class SpotifyApi implements IStreamerApi {

    private static SpotifyApi instance = null;

    private Client client;

    private SpotifyApi() {
        client = ClientBuilder.newClient();
    }

    public static SpotifyApi getInstance() {
        if(instance == null)
            instance = new SpotifyApi();
        return instance;
    }

    public void searchTrack(String trackName) {
        WebTarget resource = client.target("https://api.spotify.com/v1/search")
                .queryParam("q", trackName)
                .queryParam("type", "track")
                .queryParam("limit", "10");
        SpotifySearchReturnDTO response = resource.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + SpotifyAuthenticator.getInstance().getToken())
                .get(SpotifySearchReturnDTO.class);
        System.out.println(response);
    }
}
