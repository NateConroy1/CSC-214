package assignment07.csc214.project.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import assignment07.csc214.project.database.RunnerCursorWrapper;
import assignment07.csc214.project.database.RunnerDbSchema.*;

import java.util.ArrayList;

import assignment07.csc214.project.database.RunnersDatabaseHelper;

/**
 * Created by Nate on 3/22/17.
 */

public class RunnerCollection {

    private static RunnerCollection RUNNER_COLLECTION;
    private final Context mContext;
    private final SQLiteDatabase mDatabase;
    private final ArrayList<Runner> mRunners;

    // get runner collection instance
    public static RunnerCollection getInstance(Context context) {
        if(RUNNER_COLLECTION == null) {
            RUNNER_COLLECTION = new RunnerCollection(context);
        }
        return RUNNER_COLLECTION;
    }

    private RunnerCollection(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new RunnersDatabaseHelper(mContext).getWritableDatabase();
        mRunners = new ArrayList<>();
    }

    // function that adds a runner to the database
    public void addRunner(Runner runner) {
        ContentValues values = new ContentValues();

        values.put(RunnerTable.Cols.ID, String.valueOf(runner.getId()));
        values.put(RunnerTable.Cols.NAME, runner.getName());
        values.put(RunnerTable.Cols.YEAR, runner.getYear());
        values.put(RunnerTable.Cols.HOMETOWN, runner.getHometown());
        values.put(RunnerTable.Cols.EVENT, runner.getEvent());

        mDatabase.insert(RunnerTable.NAME, null, values);
    }

    // function that returns a cursor wrapper
    private RunnerCursorWrapper queryRunners(String where, String[] args) {
        Cursor cursor = mDatabase.query(
            RunnerTable.NAME,
            null,
            where,
            args,
            null,
            null,
            null
        );

        return new RunnerCursorWrapper(cursor);
    }

    // function that returns a list of all of the items in the SQLite database
    public ArrayList<Runner> getAllRunners() {
        mRunners.clear();
        RunnerCursorWrapper wrapper = queryRunners(null, null);

        Runner runner = null;
        if(wrapper.moveToFirst()) {
            do{
                runner = wrapper.getRunner();
                mRunners.add(runner);
            }
            while (wrapper.moveToNext());
        }

        wrapper.close();

        return mRunners;
    }
}
