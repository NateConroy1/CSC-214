package project2.csc214.databases.favorites;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nate on 4/8/17.
 */

public class FavoritesDatabaseHelper extends SQLiteOpenHelper {
    public FavoritesDatabaseHelper(Context context) {
        super(context, FavoritesSchema.DATABASE_NAME, null, FavoritesSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + FavoritesSchema.FavoritesTable.NAME
        + "(_id integer primary key autoincrement, "
        + FavoritesSchema.FavoritesTable.Cols.FAVORITER + ", "
        + FavoritesSchema.FavoritesTable.Cols.FAVORITEE + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
