package assignment07.csc214.project.database;

/**
 * Created by Nate on 3/31/17.
 */

public class RunnerDbSchema {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "runners_database.db";

    public static final class RunnerTable {
        public static final String NAME = "runners";

        public static final class Cols {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String YEAR = "year";
            public static final String HOMETOWN = "hometown";
            public static final String EVENT = "event";
        }
    }
}
