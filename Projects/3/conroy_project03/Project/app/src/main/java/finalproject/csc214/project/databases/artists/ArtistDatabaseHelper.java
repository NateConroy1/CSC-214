package finalproject.csc214.project.databases.artists;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nate on 4/29/17.
 */

public class ArtistDatabaseHelper extends SQLiteOpenHelper {

    public ArtistDatabaseHelper(Context context) {
        super(context, ArtistSchema.DATABASE_NAME, null, ArtistSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + ArtistSchema.ArtistsTable.NAME
                + "(_id integer primary key autoincrement, "
                + ArtistSchema.ArtistsTable.Cols.ID + ", "
                + ArtistSchema.ArtistsTable.Cols.NAME + ", "
                + ArtistSchema.ArtistsTable.Cols.HOMETOWN + ", "
                + ArtistSchema.ArtistsTable.Cols.GENRE + ", "
                + ArtistSchema.ArtistsTable.Cols.BIO + ", "
                + ArtistSchema.ArtistsTable.Cols.IMAGE + ", "
                + ArtistSchema.ArtistsTable.Cols.SONG1 + ", "
                + ArtistSchema.ArtistsTable.Cols.SONG2 + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
