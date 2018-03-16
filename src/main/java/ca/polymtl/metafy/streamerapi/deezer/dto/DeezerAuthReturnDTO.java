package ca.polymtl.metafy.streamerapi.deezer.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DeezerAuthReturnDTO {
    @XmlElement(name="access_token")
    private String token;

    @Override
    public String toString() {
        return token;
    }
}
