package csc214.assignment09.handlers;

/**
 * Created by Nate on 4/25/17.
 */

public class WorkPoster<W> implements Runnable {

    private final W mWork;
    private final JobListener<W> mListener;

    public WorkPoster(W work, JobListener<W> listener) {
        mWork = work;
        mListener = listener;
    }

    // override run method
    @Override
    public void run() {
        mListener.someWorkCompleted(mWork);
    }
}
