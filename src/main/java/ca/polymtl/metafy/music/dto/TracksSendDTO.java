package ca.polymtl.metafy.music.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wmouchere
 * This class is a Data Transfer Object used to send the result of a search query.
 */
@XmlRootElement
public class TracksSendDTO {

    @XmlElement(name = "tracks")
    private List<TrackSendDTO> tracks;

    public TracksSendDTO() {
        tracks = new ArrayList<>();
    }

    public TracksSendDTO(List<TrackSendDTO> tracks) {
        this.tracks = tracks;
    }
}
