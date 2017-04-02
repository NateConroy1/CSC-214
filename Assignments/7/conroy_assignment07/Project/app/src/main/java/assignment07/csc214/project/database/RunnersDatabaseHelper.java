package assignment07.csc214.project.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Nate on 3/31/17.
 */

public class RunnersDatabaseHelper extends SQLiteOpenHelper {

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + RunnerDbSchema.RunnerTable.NAME;

    public RunnersDatabaseHelper(Context context) {
        super(context, RunnerDbSchema.DATABASE_NAME, null, RunnerDbSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + RunnerDbSchema.RunnerTable.NAME
        + "(_id integer primary key autoincrement, "
        + RunnerDbSchema.RunnerTable.Cols.ID + ", "
        + RunnerDbSchema.RunnerTable.Cols.NAME + ", "
        + RunnerDbSchema.RunnerTable.Cols.YEAR + ", "
        + RunnerDbSchema.RunnerTable.Cols.HOMETOWN + ", "
        + RunnerDbSchema.RunnerTable.Cols.EVENT + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }
}
