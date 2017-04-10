package project2.csc214.databases.posts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nate on 4/6/17.
 */

public class PostsDatabaseHelper extends SQLiteOpenHelper{
    public PostsDatabaseHelper(Context context) {
        super(context, PostsSchema.DATABASE_NAME, null, PostsSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + PostsSchema.PostsTable.NAME
                + "(_id integer primary key autoincrement, "
                + PostsSchema.PostsTable.Cols.TIMESTAMP + ", "
                + PostsSchema.PostsTable.Cols.POSTER_ID + ", "
                + PostsSchema.PostsTable.Cols.POST_TEXT + ", "
                + PostsSchema.PostsTable.Cols.POST_IMAGE + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
