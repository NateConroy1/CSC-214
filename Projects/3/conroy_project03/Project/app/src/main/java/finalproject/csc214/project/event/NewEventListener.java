package finalproject.csc214.project.event;

/**
 * Created by Nate on 4/30/17.
 */

public interface NewEventListener {

    public void setDate(int year, int month, int day);

    public void setTime(int hour, int minute);
}
