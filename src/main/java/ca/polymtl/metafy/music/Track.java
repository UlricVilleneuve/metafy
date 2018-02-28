package ca.polymtl.metafy.music;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Maxence on 28/02/2018.
 * This class represent the tracks researched by the user.
 * It is used to display the result of a search or the content of a playlist.
 */

@XmlRootElement
public class Track {

    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "author")
    private String author;
    @XmlElement(name = "duration")
    private long duration;

    // Empty default constructor necessary for JSON marshalling
    public Track(){

    }

    public Track(String name, String author, long duration){
        this.name = name;
        this.author = author;
        this.duration = duration;
    }

    public String getName(){
        return this.name;
    }

    public String getAuthor(){
        return this.author;
    }

    public long getDuration(){
        return this.duration;
    }
}
