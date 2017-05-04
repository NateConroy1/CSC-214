package csc214.assignment09.handlers;

/**
 * Created by Nate on 4/25/17.
 */

public interface JobListener<W> {
    // declare someWorkCompleted method
    public void someWorkCompleted(W work);
}
