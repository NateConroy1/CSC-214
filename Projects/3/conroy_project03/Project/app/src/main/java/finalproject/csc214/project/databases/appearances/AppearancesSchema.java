package finalproject.csc214.project.databases.appearances;

/**
 * Created by Nate on 4/30/17.
 */

public class AppearancesSchema {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "appearances.db";

    public static final class AppearancesTable {
        public static final String NAME = "appearances";

        public static final class Cols {
            public static final String ARTIST_ID = "artistid";
            public static final String EVENT_ID = "eventid";
        }
    }
}
