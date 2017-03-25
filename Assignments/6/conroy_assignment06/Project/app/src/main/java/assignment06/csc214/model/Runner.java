package assignment06.csc214.model;

/**
 * Created by Nate on 3/21/17.
 */

public class Runner {
    private String mName;
    private int mYear;
    private String mHometown;
    private String mEvent;
    private boolean mCaptain;
    private String mDescription;

    public Runner(String name, int year, String hometown, String event, boolean captain, String description) {
        mName = name;
        mYear = year;
        mHometown = hometown;
        mEvent = event;
        mCaptain = captain;
        mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public int getYear() {
        return mYear;
    }

    public String getEvent() {
        return mEvent;
    }

    public boolean isCaptain() {
        return mCaptain;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getHometown() {
        return mHometown;
    }

    @Override
    public String toString() {
        String yearString = "";
        switch (mYear) {
            case 1:
                yearString = "FR";
                break;
            case 2:
                yearString  = "SO";
                break;
            case 3:
                yearString = "JR";
                break;
            case 4:
                yearString = "SR";
                break;
        }
        String captainString = "";
        if(mCaptain) {
            captainString = "* ";
        }
        return captainString + mName + ", " + yearString;
    }
}
