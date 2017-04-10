package project2.csc214.databases.favorites;

/**
 * Created by Nate on 4/8/17.
 */

public class FavoritesSchema {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "favorites.db";

    public static final class FavoritesTable {
        public static final String NAME = "favorites";

        public static final class Cols {
            public static final String FAVORITER = "favoriter";
            public static final String FAVORITEE = "favoritee";
        }
    }
}
