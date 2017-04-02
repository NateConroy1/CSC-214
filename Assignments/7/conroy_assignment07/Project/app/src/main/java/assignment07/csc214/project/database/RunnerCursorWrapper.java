package assignment07.csc214.project.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import assignment07.csc214.project.model.Runner;
import assignment07.csc214.project.database.RunnerDbSchema.RunnerTable;

/**
 * Created by Nate on 3/31/17.
 */

public class RunnerCursorWrapper extends CursorWrapper{
    public RunnerCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Runner getRunner() {
        UUID id = UUID.fromString(getString(getColumnIndex(RunnerTable.Cols.ID)));
        String name = getString(getColumnIndex(RunnerTable.Cols.NAME));
        String year = getString(getColumnIndex(RunnerTable.Cols.YEAR));
        String event = getString(getColumnIndex(RunnerTable.Cols.EVENT));
        String hometown = getString(getColumnIndex(RunnerTable.Cols.HOMETOWN));

        Runner runner = new Runner(id);
        runner.setName(name);
        runner.setYear(year);
        runner.setEvent(event);
        runner.setHometown(hometown);

        return runner;
    }
}
