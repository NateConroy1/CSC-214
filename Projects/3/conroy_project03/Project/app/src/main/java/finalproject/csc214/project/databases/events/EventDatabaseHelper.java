package finalproject.csc214.project.databases.events;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nate on 4/30/17.
 */

public class EventDatabaseHelper extends SQLiteOpenHelper{

    public EventDatabaseHelper(Context context) {
        super(context, EventSchema.DATABASE_NAME, null, EventSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + EventSchema.EventsTable.NAME
                + "(_id integer primary key autoincrement, "
                + EventSchema.EventsTable.Cols.ID + ", "
                + EventSchema.EventsTable.Cols.YEAR + ", "
                + EventSchema.EventsTable.Cols.MONTH + ", "
                + EventSchema.EventsTable.Cols.DAY + ", "
                + EventSchema.EventsTable.Cols.HOUR + ", "
                + EventSchema.EventsTable.Cols.MINUTE + ", "
                + EventSchema.EventsTable.Cols.VENUE_ID + ", "
                + EventSchema.EventsTable.Cols.DESCRIPTION + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
