package finalproject.csc214.project.model;

import java.util.UUID;

/**
 * Created by Nate on 4/30/17.
 */

public class Event {

    private UUID mId;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private UUID mVenueId;
    private String mDescription;

    public Event(UUID id) {
        if(id == null) {
            id = UUID.randomUUID();
        }
        mId = id;
    }

    public UUID getmId() {
        return mId;
    }

    public int getmYear() {
        return mYear;
    }

    public void setmYear(int mYear) {
        this.mYear = mYear;
    }

    public int getmMonth() {
        return mMonth;
    }

    public void setmMonth(int mMonth) {
        this.mMonth = mMonth;
    }

    public int getmDay() {
        return mDay;
    }

    public void setmDay(int mDay) {
        this.mDay = mDay;
    }

    public int getmHour() {
        return mHour;
    }

    public void setmHour(int mHour) {
        this.mHour = mHour;
    }

    public int getmMinute() {
        return mMinute;
    }

    public void setmMinute(int mMinute) {
        this.mMinute = mMinute;
    }

    public UUID getmVenueId() {
        return mVenueId;
    }

    public void setmVenueId(UUID mVenueId) {
        this.mVenueId = mVenueId;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
