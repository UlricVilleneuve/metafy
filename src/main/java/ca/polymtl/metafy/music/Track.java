package ca.polymtl.metafy.music;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Maxence, wmouchere
 * This class represent the tracks researched by the user.
 * It is used to display the result of a search or the content of a playlist.
 */
public class Track {

    private String name;
    private String author;
    private String url;
    private long duration;
    private String origin;

    public Track(String name, String author, String url, long duration, String origin){
        this.name = name;
        this.author = author;
        this.url = url;
        this.duration = duration;
        this.origin = origin;
    }

    public String getName(){
        return this.name;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getUrl() {
        return url;
    }

    public long getDuration(){
        return this.duration;
    }

    public String getOrigin() {
        return origin;
    }
}
