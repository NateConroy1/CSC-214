package finalproject.csc214.project.databases.artists;

/**
 * Created by Nate on 4/29/17.
 */

public class ArtistSchema {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "artists.db";

    public static final class ArtistsTable {
        public static final String NAME = "artists";

        public static final class Cols {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String HOMETOWN = "hometown";
            public static final String GENRE = "genre";
            public static final String BIO = "bio";
            public static final String IMAGE = "image";
            public static final String SONG1 = "song1";
            public static final String SONG2 = "song2";
        }
    }
}
