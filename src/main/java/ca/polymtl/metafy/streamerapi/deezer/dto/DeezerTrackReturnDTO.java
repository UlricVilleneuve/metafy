package ca.polymtl.metafy.streamerapi.deezer.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by thcoud on 18-02-28.
 */

@XmlRootElement
public class DeezerTrackReturnDTO {

    @XmlElement(name = "title")
    private String title;
    @XmlElement(name = "preview")
    private String trackURL;
    @XmlElement(name = "duration")
    private long duration;
    @XmlElement(name = "artist")
    private DeezerArtistReturnDTO artist;

    @Override
    public String toString() {
        return "DeezerTrackReturnDTO{" +
                "title='" + title + '\'' +
                ", trackURL='" + trackURL + '\'' +
                ", duration=" + duration +
                ", artist=" + artist +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getTrackURL() {
        return trackURL;
    }

    public long getDuration() {
        return duration;
    }

    public DeezerArtistReturnDTO getArtist() {
        return artist;
    }
}
