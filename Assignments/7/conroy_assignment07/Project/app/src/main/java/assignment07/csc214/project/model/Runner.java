package assignment07.csc214.project.model;

import java.util.UUID;

/**
 * Created by Nate on 3/21/17.
 */

public class Runner {
    private UUID mId;
    private String mName;
    private String mYear;
    private String mHometown;
    private String mEvent;

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setYear(String mYear) {
        this.mYear = mYear;
    }

    public void setHometown(String mHometown) {
        this.mHometown = mHometown;
    }

    public void setEvent(String mEvent) {
        this.mEvent = mEvent;
    }

    public Runner(UUID id) {
        if(id == null) {
            mId = UUID.randomUUID();
        }
        else {
            mId = id;
        }
    }

    public String getName() {
        return mName;
    }

    public String getYear() {
        return mYear;
    }

    public String getEvent() {
        return mEvent;
    }

    public String getHometown() {
        return mHometown;
    }

    public UUID getId() {
        return mId;
    }
}
