package ca.polymtl.metafy.streamerapi;

import ca.polymtl.metafy.music.Track;

import java.util.List;

public interface IStreamerApi {

    /**
     * Search for a track on the streaming service API. This method runs an HTTP query.
     * @param queryString A String used to perform the search
     * @return A List of Music holding the results of the query
     */
    List<Track> searchTrack(String queryString);

}