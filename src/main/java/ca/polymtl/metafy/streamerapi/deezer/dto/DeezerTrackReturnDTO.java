package ca.polymtl.metafy.streamerapi.deezer.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by thcoud on 18-02-28.
 */

@XmlRootElement
public class DeezerTrackReturnDTO {
    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "preview")
    private String trackURL;

    @XmlElement(name = "message")
    private String errorMsg;

    public String toString() {
        return "msg :" + errorMsg + ", title :" + title;
    }
}
