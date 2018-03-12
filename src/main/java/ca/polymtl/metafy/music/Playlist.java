package ca.polymtl.metafy.music;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxence, wmouchere
 * This class represents the list of tracks created by the user.
 * It is used by the music player to play each track after an other
 */
public class Playlist {

    private String name;
    private List<Track> tracks;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

}
