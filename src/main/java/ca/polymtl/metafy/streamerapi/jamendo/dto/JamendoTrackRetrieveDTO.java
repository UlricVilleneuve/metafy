package ca.polymtl.metafy.streamerapi.jamendo.dto;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class JamendoTrackRetrieveDTO {

    @XmlElement(name = "name")
    private String trackName;

    @XmlElement(name = "artist_name")
    private String artistName;

    @XmlElement(name = "duration")
    private String trackDuration;

    @XmlElement(name = "audio")
    private String trackURL;

    public String getTrackName() {
        return trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getTrackDuration() {
        return trackDuration;
    }

    public String getTrackURL() {
        return trackURL;
    }

    @Override
    public String toString() {
        return "JamendoTrackRetrieveDTO{" +
                "trackName='" + trackName + '\'' +
                ", artist=" + artistName +
                ", duration=" + trackDuration +
                ", trackURL='" + trackURL + '\'' +
                '}';
    }
}
