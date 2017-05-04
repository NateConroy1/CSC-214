package finalproject.csc214.project.model;

import java.util.UUID;

/**
 * Created by Nate on 4/30/17.
 */

public class Appearance {

    // represents a relationship between an artist and an event

    private UUID mArtistId;
    private UUID mEventId;

    public Appearance() {
    }


    public UUID getArtistId() {
        return mArtistId;
    }

    public void setArtistId(UUID artistId) {
        this.mArtistId = artistId;
    }

    public UUID getEventId() {
        return mEventId;
    }

    public void setEventId(UUID eventId) {
        this.mEventId = eventId;
    }
}
