package finalproject.csc214.project.databases.events;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import finalproject.csc214.project.model.Event;

/**
 * Created by Nate on 4/30/17.
 */

public class EventCursorWrapper extends CursorWrapper {

    public EventCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Event getEvent() {
        UUID id = UUID.fromString(getString(getColumnIndex(EventSchema.EventsTable.Cols.ID)));
        int year = Integer.valueOf(getString(getColumnIndex(EventSchema.EventsTable.Cols.YEAR)));
        int month = Integer.valueOf(getString(getColumnIndex(EventSchema.EventsTable.Cols.MONTH)));
        int day = Integer.valueOf(getString(getColumnIndex(EventSchema.EventsTable.Cols.DAY)));
        int hour = Integer.valueOf(getString(getColumnIndex(EventSchema.EventsTable.Cols.HOUR)));
        int minute = Integer.valueOf(getString(getColumnIndex(EventSchema.EventsTable.Cols.MINUTE)));
        UUID venueId = UUID.fromString(getString(getColumnIndex(EventSchema.EventsTable.Cols.VENUE_ID)));
        String description = getString(getColumnIndex(EventSchema.EventsTable.Cols.DESCRIPTION));

        Event event = new Event(id);

        event.setmYear(year);
        event.setmMonth(month);
        event.setmDay(day);
        event.setmHour(hour);
        event.setmMinute(minute);
        event.setmVenueId(venueId);
        event.setmDescription(description);

        return event;
    }
}
