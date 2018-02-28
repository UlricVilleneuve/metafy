package ca.polymtl.metafy.music;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxence on 28/02/2018.
 * This class represents the list of tracks created by the user.
 * It is used by the music player to play each track after an other
 */

@XmlRootElement
public class Playlist {

    @XmlElement
    private String name;

    @XmlElement(name = "tracks")
    private List<Track> tracks;

    // Empty default constructor necessary for JSON marshalling
    public Playlist() {
        this.tracks = new ArrayList<Track>();
        tracks.add(new Track("Galway Girl","Ed Sheeran",(3*60+20)*100));
        tracks.add(new Track("Perfect","Ed Sheeran",(2*60+40)*100));
    }

    public Playlist(String name){
        this.name = name;
        this.tracks = new ArrayList<Track>();
    }

    /**
     * Add a track to the playlist
     * @param t track to be added to the list
     */
    public void addTrack(Track t){
        this.tracks.add(t);
    }
}
