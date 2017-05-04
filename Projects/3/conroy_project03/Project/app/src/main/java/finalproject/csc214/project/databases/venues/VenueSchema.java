package finalproject.csc214.project.databases.venues;

/**
 * Created by Nate on 4/29/17.
 */

public class VenueSchema {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "venues.db";

    public static final class VenuesTable {
        public static final String NAME = "venues";

        public static final class Cols {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String LOCATION = "location";
            public static final String DESCRIPTION = "description";
            public static final String LATITUDE = "latitude";
            public static final String LONGITUDE = "longitude";
            public static final String IMAGE = "image";
        }
    }
}
