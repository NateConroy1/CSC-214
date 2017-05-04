package finalproject.csc214.project.databases.login_info;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nate on 4/29/17.
 */

public class UserLoginInfoDatabaseHelper extends SQLiteOpenHelper{

    public UserLoginInfoDatabaseHelper(Context context) {
        super(context, UserLoginInfoSchema.DATABASE_NAME, null, UserLoginInfoSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + UserLoginInfoSchema.UserLoginInfoTable.NAME
                + "(_id integer primary key autoincrement, "
                + UserLoginInfoSchema.UserLoginInfoTable.Cols.ID + ", "
                + UserLoginInfoSchema.UserLoginInfoTable.Cols.EMAIL + ", "
                + UserLoginInfoSchema.UserLoginInfoTable.Cols.PASSWORD + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
