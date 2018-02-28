package ca.polymtl.metafy.streamerapi.authentication;

import ca.polymtl.metafy.ApiKeyLoader;
import ca.polymtl.metafy.ApiKeyNotFoundException;
import ca.polymtl.metafy.streamerapi.authentication.dto.SpotifyAuthenticationReturnDTO;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

public class SpotifyAuthenticator implements IAuthenticator {

    private static final SpotifyAuthenticator INSTANCE = new SpotifyAuthenticator();
    private static final Logger LOGGER = Logger.getLogger(SpotifyAuthenticator.class.getName());

    private static String apiKey;

    static {
        try {
            apiKey = ApiKeyLoader.getInstance().getApiKey("Spotify");
        } catch (ApiKeyNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private String token;
    private long tokenExpirationDate;

    private SpotifyAuthenticator() {
        refreshToken();
    }

    public static SpotifyAuthenticator getInstance() {
        return INSTANCE;
    }

    public String getToken() {
        if(System.currentTimeMillis() > tokenExpirationDate)
            refreshToken();
        return token;
    }

    public void refreshToken() {
        Client client = ClientBuilder.newClient();

        WebTarget resource = client.target("https://accounts.spotify.com/api/token");
        Form form = new Form();
        form.param("grant_type", "client_credentials");

        Invocation.Builder request = resource.request();
        request.accept(MediaType.APPLICATION_JSON);
        request.header("Authorization", "Basic " + apiKey);
        SpotifyAuthenticationReturnDTO response = request.post(Entity.form(form), SpotifyAuthenticationReturnDTO.class);

        token = response.getAccessToken();
        tokenExpirationDate = System.currentTimeMillis() + response.getDuration();
    }
}
