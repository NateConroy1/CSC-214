package finalproject.csc214.project.band;

import finalproject.csc214.project.model.Artist;
import finalproject.csc214.project.model.Event;
import finalproject.csc214.project.model.Venue;

/**
 * Created by Nate on 4/30/17.
 */

public interface CallbackInterface {

    void getImage(String url);
    void clickedArtist(Artist artist);
    void clickedVenue(Venue venue);
    void clickedEvent(Event event);
}
