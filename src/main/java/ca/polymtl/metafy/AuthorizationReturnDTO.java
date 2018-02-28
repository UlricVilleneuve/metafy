package ca.polymtl.metafy;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthorizationReturnDTO {

    @XmlElement(name = "access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        return "AuthorizationReturnDTO{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}
