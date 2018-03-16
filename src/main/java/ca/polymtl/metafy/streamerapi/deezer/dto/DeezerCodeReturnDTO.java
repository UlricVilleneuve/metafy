package ca.polymtl.metafy.streamerapi.deezer.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is a data object for the code Deezer returns when a user connect logs in through the app.
 * @author Théo Coulin
 */

@XmlRootElement
public class DeezerCodeReturnDTO {
    @XmlElement(name = "code")
    private String deezerCode;

    public DeezerCodeReturnDTO() {

    }

    public String getDeezerCode() {
        return deezerCode;
    }
}
