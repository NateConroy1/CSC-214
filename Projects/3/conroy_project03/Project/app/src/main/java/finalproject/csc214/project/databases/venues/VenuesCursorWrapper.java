package finalproject.csc214.project.databases.venues;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import finalproject.csc214.project.model.Venue;

/**
 * Created by Nate on 4/29/17.
 */

public class VenuesCursorWrapper extends CursorWrapper {

    public VenuesCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Venue getVenue() {
        UUID id = UUID.fromString(getString(getColumnIndex(VenueSchema.VenuesTable.Cols.ID)));
        String name = getString(getColumnIndex(VenueSchema.VenuesTable.Cols.NAME));
        String location = getString(getColumnIndex(VenueSchema.VenuesTable.Cols.LOCATION));
        String description = getString(getColumnIndex(VenueSchema.VenuesTable.Cols.DESCRIPTION));
        double latitude = Double.valueOf(getString(getColumnIndex(VenueSchema.VenuesTable.Cols.LATITUDE)));
        double longitude = Double.valueOf(getString(getColumnIndex(VenueSchema.VenuesTable.Cols.LONGITUDE)));
        String image = getString(getColumnIndex(VenueSchema.VenuesTable.Cols.IMAGE));

        Venue venue = new Venue(id);

        venue.setName(name);
        venue.setLocation(location);
        venue.setDescription(description);
        venue.setLatitude(latitude);
        venue.setLongitude(longitude);
        venue.setImagePath(image);

        return venue;
    }
}
