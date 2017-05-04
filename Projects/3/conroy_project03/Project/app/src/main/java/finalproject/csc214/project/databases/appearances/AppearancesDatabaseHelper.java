package finalproject.csc214.project.databases.appearances;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nate on 4/30/17.
 */

public class AppearancesDatabaseHelper extends SQLiteOpenHelper{

    public AppearancesDatabaseHelper(Context context) {
        super(context, AppearancesSchema.DATABASE_NAME, null, AppearancesSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + AppearancesSchema.AppearancesTable.NAME
                + "(_id integer primary key autoincrement, "
                + AppearancesSchema.AppearancesTable.Cols.ARTIST_ID + ", "
                + AppearancesSchema.AppearancesTable.Cols.EVENT_ID + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
