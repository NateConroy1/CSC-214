package finalproject.csc214.project.databases.events;

/**
 * Created by Nate on 4/30/17.
 */

public class EventSchema {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "events.db";

    public static final class EventsTable {
        public static final String NAME = "events";

        public static final class Cols {
            public static final String ID = "id";
            public static final String YEAR = "year";
            public static final String MONTH = "month";
            public static final String DAY = "day";
            public static final String HOUR = "hour";
            public static final String MINUTE = "minute";
            public static final String VENUE_ID = "venueid";
            public static final String DESCRIPTION = "description";
        }
    }
}
