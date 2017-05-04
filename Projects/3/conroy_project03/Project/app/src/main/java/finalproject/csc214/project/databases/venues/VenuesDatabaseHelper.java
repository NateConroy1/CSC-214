package finalproject.csc214.project.databases.venues;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nate on 4/29/17.
 */

public class VenuesDatabaseHelper extends SQLiteOpenHelper{

    public VenuesDatabaseHelper(Context context) {
        super(context, VenueSchema.DATABASE_NAME, null, VenueSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + VenueSchema.VenuesTable.NAME
                + "(_id integer primary key autoincrement, "
                + VenueSchema.VenuesTable.Cols.ID + ", "
                + VenueSchema.VenuesTable.Cols.NAME + ", "
                + VenueSchema.VenuesTable.Cols.LOCATION + ", "
                + VenueSchema.VenuesTable.Cols.DESCRIPTION + ", "
                + VenueSchema.VenuesTable.Cols.LATITUDE + ", "
                + VenueSchema.VenuesTable.Cols.LONGITUDE + ", "
                + VenueSchema.VenuesTable.Cols.IMAGE + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
