package ca.polymtl.metafy.streamerapi.spotify.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SpotifySearchReturnDTO {

    @XmlElement(name = "tracks")
    private SpotifyTracksReturnDTO tracksReturnDTO;

    public SpotifyTracksReturnDTO getTracksReturnDTO() {
        return tracksReturnDTO;
    }

    @Override
    public String toString() {
        return "SpotifySearchReturnDTO{" +
                "tracksReturnDTO=" + tracksReturnDTO +
                '}';
    }
}
