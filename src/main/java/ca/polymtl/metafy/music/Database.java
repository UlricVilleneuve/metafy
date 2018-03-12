package ca.polymtl.metafy.music;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxence on 28/02/2018.
 * Dummy class to create some Tracks and Playlists for test purpose
 * Will be deleted in a near futur
 */
@XmlRootElement
public class Database {

    @XmlElement
    private List<Playlist> playlists;

    public Database(){
        this.playlists = new ArrayList<Playlist>();
        Playlist play1 = new Playlist("Playlist Ed Sheeran");
        play1.addTrack(new Track("Galway Girl","Ed Sheeran",(3*60+20)*100));
        play1.addTrack(new Track("Perfect","Ed Sheeran",(4*60+10)*100));

        Playlist play2 = new Playlist("Playlist ACDC");
        play2.addTrack(new Track("ThunderStruck","AC/DC",(3*60+50)*100));
        play2.addTrack(new Track("Back in Black","AC/DC",(4*60+40)*100));

        this.playlists.add(play1);
        this.playlists.add(play2);
    }

    public void addPlaylist(Playlist p){
        this.playlists.add(p);
    }

    public List<Playlist> getDatabase(){
        return this.playlists;
    }
}
