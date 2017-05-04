package finalproject.csc214.project.databases.login_info;

/**
 * Created by Nate on 4/29/17.
 */

public class UserLoginInfoSchema {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "user_login_info.db";

    public static final class UserLoginInfoTable {
        public static final String NAME = "user_login_info";

        public static final class Cols {
            public static final String ID = "id";
            public static final String EMAIL = "email";
            public static final String PASSWORD = "password";
        }
    }
}
