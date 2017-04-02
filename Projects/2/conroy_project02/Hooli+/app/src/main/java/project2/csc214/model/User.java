package project2.csc214.model;

/**
 * Created by Nate on 3/26/17.
 */

public class User {

    public static int MALE = 0;
    public static int FEMALE = 1;

    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mPassword;
    private int mBirthYear;
    private int mBirthMonth;
    private int mBirthDayOfMonth;
    private boolean mValidBirthday;
    private int mGender;

    public User() {
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public int getBirthYear() {
        return mBirthYear;
    }

    public void setBirthYear(int mBirthYear) {
        this.mBirthYear = mBirthYear;
    }

    public int getBirthMonth() {
        return mBirthMonth;
    }

    public void setBirthMonth(int mBirthMonth) {
        this.mBirthMonth = mBirthMonth;
    }

    public int getBirthDayOfMonth() {
        return mBirthDayOfMonth;
    }

    public void setBirthDayOfMonth(int mBirthDayOfMonth) {
        this.mBirthDayOfMonth = mBirthDayOfMonth;
    }

    public boolean isValidBirthday() {
        return mValidBirthday;
    }

    public void setValidBirthday(boolean mValidBirthday) {
        this.mValidBirthday = mValidBirthday;
    }

    public int getGender() {
        return mGender;
    }

    public void setGender(int mGender) {
        this.mGender = mGender;
    }
}
