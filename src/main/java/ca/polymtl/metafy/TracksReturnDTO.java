package ca.polymtl.metafy;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class TracksReturnDTO {

    @XmlElement(name = "items")
    private List<TrackReturnDTO> items;

    public List<TrackReturnDTO> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "TracksReturnDTO{" +
                "items=" + items +
                '}';
    }
}
