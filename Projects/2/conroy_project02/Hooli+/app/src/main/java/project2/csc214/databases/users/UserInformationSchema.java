package project2.csc214.databases.users;

/**
 * Created by Nate on 4/5/17.
 */

public class UserInformationSchema {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "user_information.db";

    public static final class UserInformationTable {
        public static final String NAME = "user_information";

        public static final class Cols {
            public static final String ID = "id";
            public static final String FIRST_NAME = "first_name";
            public static final String LAST_NAME = "last_name";
            public static final String EMAIL = "email";
            public static final String PASSWORD = "password";
            public static final String BIRTH_YEAR = "birth_year";
            public static final String BIRTH_MONTH = "birth_month";
            public static final String BIRTH_DAY = "birth_day";
            public static final String GENDER = "gender";
            public static final String HOMETOWN = "hometown";
            public static final String BIO_DESCRIPTION = "bio_description";
            public static final String PROFILE_PICTURE = "profile_picture";
        }
    }
}
