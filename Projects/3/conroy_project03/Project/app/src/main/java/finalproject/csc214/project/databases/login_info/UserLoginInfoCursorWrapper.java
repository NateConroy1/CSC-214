package finalproject.csc214.project.databases.login_info;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import finalproject.csc214.project.model.User;

/**
 * Created by Nate on 4/29/17.
 */

public class UserLoginInfoCursorWrapper extends CursorWrapper {

    public UserLoginInfoCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser() {
        UUID id = UUID.fromString(getString(getColumnIndex(UserLoginInfoSchema.UserLoginInfoTable.Cols.ID)));
        String email = getString(getColumnIndex(UserLoginInfoSchema.UserLoginInfoTable.Cols.EMAIL));
        String password = getString(getColumnIndex(UserLoginInfoSchema.UserLoginInfoTable.Cols.PASSWORD));

        User user = new User(id);

        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}
