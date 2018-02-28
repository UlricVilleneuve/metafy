package ca.polymtl.metafy;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthorizationReturnDTO {

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
        return "AuthorizationReturnDTO{" +
                "accessToken='" + accessToken + '\'' +
                ", duration=" + duration +
                '}';
    }
}
