package finalproject.csc214.project.databases.appearances;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import finalproject.csc214.project.model.Appearance;

/**
 * Created by Nate on 4/30/17.
 */

public class AppearancesCursorWrapper extends CursorWrapper {

    public AppearancesCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Appearance getAppearance() {
        UUID artistId = UUID.fromString(getString(getColumnIndex(AppearancesSchema.AppearancesTable.Cols.ARTIST_ID)));
        UUID eventId = UUID.fromString(getString(getColumnIndex(AppearancesSchema.AppearancesTable.Cols.EVENT_ID)));

        Appearance appearance = new Appearance();

        appearance.setArtistId(artistId);
        appearance.setEventId(eventId);

        return appearance;
    }
}
