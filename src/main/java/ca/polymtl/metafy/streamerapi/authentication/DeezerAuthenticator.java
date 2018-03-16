package ca.polymtl.metafy.streamerapi.authentication;

import java.util.logging.Logger;

public class DeezerAuthenticator implements IAuthenticator {

    private static DeezerAuthenticator instance = null;
    private static final Logger LOGGER = Logger.getLogger(DeezerAuthenticator.class.getName());

    //the token is non expirable
    private static String token;

    /*static {
        try {
            token = ApiKeyLoader.getInstance().getApiKey("deezer");
        } catch (ApiKeyNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }*/

    private DeezerAuthenticator() {};

    public static DeezerAuthenticator getInstance() {
        if (instance == null) {
            instance = new DeezerAuthenticator();
        }
        return instance;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void refreshToken() {

    }
}
