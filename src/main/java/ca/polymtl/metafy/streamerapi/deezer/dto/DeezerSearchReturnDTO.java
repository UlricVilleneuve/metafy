package ca.polymtl.metafy.streamerapi.deezer.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by thcoud on 18-02-28.
 */

@XmlRootElement
public class DeezerSearchReturnDTO {
    @XmlElement(name = "data")
    private List<DeezerTrackReturnDTO> items;

    @Override
    public String toString() {
        String res="";
        for (DeezerTrackReturnDTO o : items) {
            res+= o.toString();
        }
        return res;
    }
}
