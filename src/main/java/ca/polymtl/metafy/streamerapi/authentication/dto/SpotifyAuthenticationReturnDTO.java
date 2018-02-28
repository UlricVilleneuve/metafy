package ca.polymtl.metafy.streamerapi.authentication.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Author wmouchere
 *
 */
@XmlRootElement
public class SpotifyAuthenticationReturnDTO {

    @XmlElement(name = "access_token")
    private String accessToken;

    @XmlElement(name = "expires_in")
    private long duration;

    public String getAccessToken() {
        return accessToken;
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "SpotifyAuthenticationReturnDTO{" +
                "accessToken='" + accessToken + '\'' +
                ", duration=" + duration +
                '}';
    }
}
