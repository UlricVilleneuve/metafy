package ca.polymtl.metafy.music.dto;

import ca.polymtl.metafy.music.Track;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author wmouchere
 * This class is a Data Transfer Object used to send Track data.
 */
@XmlRootElement
public class TrackSendDTO {

    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "author")
    private String author;
    @XmlElement(name = "url")
    private String url;
    @XmlElement(name = "duration")
    private long duration;
    @XmlElement(name = "origin")
    private String origin;

    // Empty default constructor necessary for JSON marshalling
    public TrackSendDTO(){

    }

    public TrackSendDTO(String name, String author, String url, long duration, String origin){
        this.name = name;
        this.author = author;
        this.url = url;
        this.duration = duration;
        this.origin = origin;
    }

    public TrackSendDTO(Track track){
        this.name = track.getName();
        this.author = track.getAuthor();
        this.url = track.getUrl();
        this.duration = track.getDuration();
        this.origin = track.getOrigin();
    }
}
