package ca.polymtl.metafy;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TrackReturnDTO {

    @XmlElement(name = "name")
    private String trackName;

    @XmlElement(name = "preview_url")
    private String trackURL;

    public String getTrackName() {
        return trackName;
    }

    public String getTrackURL() {
        return trackURL;
    }

    @Override
    public String toString() {
        return "TrackReturnDTO{" +
                "trackName='" + trackName + '\'' +
                ", trackURL='" + trackURL + '\'' +
                '}';
    }
}
