package ca.polymtl.metafy;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchReturnDTO {

    @XmlElement(name = "tracks")
    private TracksReturnDTO tracksReturnDTO;

    public TracksReturnDTO getTracksReturnDTO() {
        return tracksReturnDTO;
    }

    @Override
    public String toString() {
        return "SearchReturnDTO{" +
                "tracksReturnDTO=" + tracksReturnDTO +
                '}';
    }
}
