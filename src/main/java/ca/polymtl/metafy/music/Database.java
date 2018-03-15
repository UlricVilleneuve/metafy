package ca.polymtl.metafy.music;

import ca.polymtl.metafy.music.dto.PlaylistSendDTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxence
 * Dummy class to create some Tracks and Playlists for test purpose
 * Will be deleted in a near futur
 */
@XmlRootElement
public class Database {

    private List<Playlist> playlists;
    @XmlElement
    private List<PlaylistSendDTO> dtos;

    public Database(){
        this.dtos = new ArrayList<>();
        this.playlists = new ArrayList<Playlist>();
        Playlist play1 = new Playlist("Playlist Ed Sheeran");
        play1.addTrack(new Track("Galway Girl","Ed Sheeran","http://foo.bar",(3*60+20)*100, "raw"));
        play1.addTrack(new Track("Perfect","Ed Sheeran","http://foo.bar",(4*60+10)*100, "raw"));

        Playlist play2 = new Playlist("Playlist ACDC");
        play2.addTrack(new Track("ThunderStruck","AC/DC","http://foo.bar",(3*60+50)*100, "raw"));
        play2.addTrack(new Track("Back in Black","AC/DC","http://foo.bar",(4*60+40)*100, "raw"));

        this.playlists.add(play1);
        this.playlists.add(play2);

        for(Playlist p : playlists) dtos.add(new PlaylistSendDTO(p));
    }

    public void addPlaylist(Playlist p){
        this.playlists.add(p);
    }

    public List<Playlist> getDatabase(){
        return this.playlists;
    }

    public List<PlaylistSendDTO> getDatabaseDTO(){
        return this.dtos;
    }
}
