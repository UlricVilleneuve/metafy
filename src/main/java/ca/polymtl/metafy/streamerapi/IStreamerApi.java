package ca.polymtl.metafy.streamerapi;

/**
 * @author wmouchere
 * Interface representing the possible actions on a music streaming service API.
 */
public interface IStreamerApi {

    /**
     * Search for a track on the streaming service API. This method runs an HTTP query.
     * @param trackName A String representing name of the track searched
     * @return A List of Music holding the results of the query
     */
    void searchTrack(String trackName);

}
