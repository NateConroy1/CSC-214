package project2.csc214.databases.users;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nate on 4/5/17.
 */

public class UserInfoDatabaseHelper extends SQLiteOpenHelper {

    public UserInfoDatabaseHelper(Context context) {
        super(context, UserInformationSchema.DATABASE_NAME, null, UserInformationSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + UserInformationSchema.UserInformationTable.NAME
                + "(_id integer primary key autoincrement, "
                + UserInformationSchema.UserInformationTable.Cols.ID + ", "
                + UserInformationSchema.UserInformationTable.Cols.FIRST_NAME + ", "
                + UserInformationSchema.UserInformationTable.Cols.LAST_NAME + ", "
                + UserInformationSchema.UserInformationTable.Cols.EMAIL + ", "
                + UserInformationSchema.UserInformationTable.Cols.PASSWORD + ", "
                + UserInformationSchema.UserInformationTable.Cols.BIRTH_YEAR + ", "
                + UserInformationSchema.UserInformationTable.Cols.BIRTH_MONTH + ", "
                + UserInformationSchema.UserInformationTable.Cols.BIRTH_DAY + ", "
                + UserInformationSchema.UserInformationTable.Cols.GENDER + ", "
                + UserInformationSchema.UserInformationTable.Cols.HOMETOWN + ", "
                + UserInformationSchema.UserInformationTable.Cols.BIO_DESCRIPTION + ", "
                + UserInformationSchema.UserInformationTable.Cols.PROFILE_PICTURE + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
