package ca.polymtl.metafy.music.dto;

import ca.polymtl.metafy.music.Playlist;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wmouchere
 * This class is a Data Transfer Object used to send Playlist data.
 */
@XmlRootElement
public class PlaylistSendDTO {

    @XmlElement
    private String name;
    @XmlElement(name = "tracks")
    private List<TrackSendDTO> tracks;

    // Empty default constructor necessary for JSON marshalling
    public PlaylistSendDTO() {
        this.tracks = new ArrayList<TrackSendDTO>();
        tracks.add(new TrackSendDTO("Galway Girl","Ed Sheeran", "http://foo.bar", (3*60+20)*100, "raw"));
        tracks.add(new TrackSendDTO("Perfect","Ed Sheeran", "http://foo.bar",(2*60+40)*100, "raw"));
    }

    public PlaylistSendDTO(Playlist playlist) {
        this.name = playlist.getName();
        this.tracks = playlist.getTracks().stream()
                .map(track -> new TrackSendDTO(track))
                .collect(Collectors.toList());
    }
}
