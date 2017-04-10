package project2.csc214.databases.posts;

/**
 * Created by Nate on 4/6/17.
 */

public class PostsSchema {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "posts.db";

    public static final class PostsTable {
        public static final String NAME = "posts";

        public static final class Cols {
            public static final String TIMESTAMP = "timestamp";
            public static final String POSTER_ID = "poster_id";
            public static final String POST_TEXT = "post_text";
            public static final String POST_IMAGE = "post_image";
        }
    }
}
