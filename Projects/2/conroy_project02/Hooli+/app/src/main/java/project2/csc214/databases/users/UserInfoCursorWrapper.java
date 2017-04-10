package project2.csc214.databases.users;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import project2.csc214.model.User;

/**
 * Created by Nate on 4/5/17.
 */

public class UserInfoCursorWrapper extends CursorWrapper {

    public UserInfoCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser() {
        UUID id = UUID.fromString(getString(getColumnIndex(UserInformationSchema.UserInformationTable.Cols.ID)));
        String firstName = getString(getColumnIndex(UserInformationSchema.UserInformationTable.Cols.FIRST_NAME));
        String lastName = getString(getColumnIndex(UserInformationSchema.UserInformationTable.Cols.LAST_NAME));
        String email = getString(getColumnIndex(UserInformationSchema.UserInformationTable.Cols.EMAIL));
        String password = getString(getColumnIndex(UserInformationSchema.UserInformationTable.Cols.PASSWORD));
        int birthYear = Integer.valueOf(getString(getColumnIndex(UserInformationSchema.UserInformationTable.Cols.BIRTH_YEAR)));
        int birthMonth = Integer.valueOf(getString(getColumnIndex(UserInformationSchema.UserInformationTable.Cols.BIRTH_MONTH)));
        int birthDay = Integer.valueOf(getString(getColumnIndex(UserInformationSchema.UserInformationTable.Cols.BIRTH_DAY)));
        int gender = Integer.valueOf(getString(getColumnIndex(UserInformationSchema.UserInformationTable.Cols.GENDER)));
        String hometown = getString(getColumnIndex(UserInformationSchema.UserInformationTable.Cols.HOMETOWN));
        String bioDescription = getString(getColumnIndex(UserInformationSchema.UserInformationTable.Cols.BIO_DESCRIPTION));
        String profilePicture = getString(getColumnIndex(UserInformationSchema.UserInformationTable.Cols.PROFILE_PICTURE));

        User user = new User();

        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setBirthYear(birthYear);
        user.setBirthMonth(birthMonth);
        user.setBirthDayOfMonth(birthDay);
        user.setGender(gender);
        user.setHometown(hometown);
        user.setBioDescription(bioDescription);
        user.setProfilePicture(profilePicture);

        return user;
    }
}
