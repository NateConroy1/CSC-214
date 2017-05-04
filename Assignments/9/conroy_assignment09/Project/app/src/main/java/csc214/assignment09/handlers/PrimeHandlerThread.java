package csc214.assignment09.handlers;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import csc214.assignment09.Calculator;

/**
 * Created by Nate on 4/25/17.
 */

public class PrimeHandlerThread extends HandlerThread{

    static final int MESSAGE_PRIME = 1;
    private static final String TAG = "PrimeHandlerTag";
    private Handler mHandler;
    private Handler mResponseHandler;
    private PrimeListener mListener;

    // create listener interface
    public interface PrimeListener extends JobListener<String> {}

    public PrimeHandlerThread(Handler responseHandler) {
        super(TAG);
        mResponseHandler = responseHandler;
    }

    // set listener
    public void setPrimeListener(PrimeListener listener) {
        mListener = listener;
    }

    // perform the prime calculation and pass the result
    public void calculatePrime(String num) {
        String message = String.valueOf(Calculator.calculateLargestPrimeNumber(Long.valueOf(num)));
        mHandler.obtainMessage(MESSAGE_PRIME, message).sendToTarget();
    }

    // set the Handler
    @Override
    protected void onLooperPrepared() {
        mHandler = new PrimeHandler(mResponseHandler, mListener);
    }

    private static class PrimeHandler extends Handler {
        private final Handler mResponseHandler;
        private final PrimeListener mListener;

        public PrimeHandler(Handler responseHandler, PrimeListener listener) {
            mResponseHandler = responseHandler;
            mListener = listener;
        }

        // handle the message
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == PrimeHandlerThread.MESSAGE_PRIME) {
                final String message = msg.obj.toString();
                mResponseHandler.post(new WorkPoster<String>(message, mListener));
            }
        }
    }
}
