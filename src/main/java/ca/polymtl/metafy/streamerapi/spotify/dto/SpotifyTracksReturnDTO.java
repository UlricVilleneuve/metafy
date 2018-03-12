package ca.polymtl.metafy.streamerapi.spotify.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class SpotifyTracksReturnDTO {

    @XmlElement(name = "items")
    private List<SpotifyTrackReturnDTO> items;

    public List<SpotifyTrackReturnDTO> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "SpotifyTracksReturnDTO{" +
                "items=" + items +
                '}';
    }
}
