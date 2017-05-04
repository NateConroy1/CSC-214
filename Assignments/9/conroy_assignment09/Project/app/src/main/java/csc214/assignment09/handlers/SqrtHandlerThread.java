package csc214.assignment09.handlers;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import csc214.assignment09.Calculator;

/**
 * Created by Nate on 4/25/17.
 */

public class SqrtHandlerThread extends HandlerThread{

    static final int MESSAGE_SQRT = 2;
    private static final String TAG = "SqrtHandlerTag";
    private Handler mHandler;
    private Handler mResponseHandler;
    private SqrtListener mListener;

    // create listener interface
    public interface SqrtListener extends JobListener<String> {}

    public SqrtHandlerThread(Handler responseHandler) {
        super(TAG);
        mResponseHandler = responseHandler;
    }

    // set listener
    public void setSqrtListener(SqrtListener listener) {
        mListener = listener;
    }

    // perform the square root calculation and pass the result
    public void calculateSqrt(String num) {
        String message = String.valueOf(Calculator.calculateSquareRoot(Long.valueOf(num)));
        mHandler.obtainMessage(MESSAGE_SQRT, message).sendToTarget();
    }

    // set the handler
    @Override
    protected void onLooperPrepared() {
        mHandler = new SqrtHandler(mResponseHandler, mListener);
    }

    private static class SqrtHandler extends Handler {
        private final Handler mResponseHandler;
        private final SqrtListener mListener;

        public SqrtHandler(Handler responseHandler, SqrtListener listener) {
            mResponseHandler = responseHandler;
            mListener = listener;
        }

        // handle the message
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == SqrtHandlerThread.MESSAGE_SQRT) {
                final String message = msg.obj.toString();
                mResponseHandler.post(new WorkPoster<String>(message, mListener));
            }
        }
    }
}
