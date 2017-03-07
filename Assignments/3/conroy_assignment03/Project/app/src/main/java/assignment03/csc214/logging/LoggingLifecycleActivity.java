package assignment03.csc214.logging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoggingLifecycleActivity extends AppCompatActivity {

    static final String CLICK_COUNT = "clickCount";
    static final String ROTATE_COUNT = "rotateCount";

    private static final String NATES_TAG = "NatesTag";

    private int mClickCount = 0;
    private int mRotateCount = 0;
    private ImageButton mImageButton;
    private ImageView mImageView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(NATES_TAG, "onCreate method called");
        setContentView(R.layout.activity_logging_lifecycle);

        // restore values after orientation change
        if(savedInstanceState != null) {
            mClickCount = savedInstanceState.getInt(CLICK_COUNT);
            mRotateCount = savedInstanceState.getInt(ROTATE_COUNT) + 1;
        }

        mImageButton = (ImageButton) findViewById(R.id.imageButton1);
        mImageView = (ImageView) findViewById(R.id.imageView1);
        mTextView = (TextView) findViewById(R.id.textView1);

        // update imageView with proper image, and textView with proper string
        updateImageView(mImageView, mClickCount);
        updateTextView(mTextView, mRotateCount);

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoggingLifecycleActivity.this, R.string.nooice, Toast.LENGTH_SHORT).show();
                mClickCount++;
                updateImageView(mImageView, mClickCount);
            }
        });
    }

    protected void updateImageView(ImageView imageView, int clickCount) {
        // method that sets the imageView image
        if(clickCount % 4 == 0) {
            imageView.setImageResource(R.drawable.kp1);
        }
        else if(clickCount % 4 == 1) {
            imageView.setImageResource(R.drawable.kp2);
        }
        else if(clickCount % 4 == 2) {
            imageView.setImageResource(R.drawable.kp3);
        }
        else {
            imageView.setImageResource(R.drawable.kp4);
        }
    }

    protected void updateTextView(TextView textView, int rotateCount) {
        // method that sets the TextView text
        textView.setText("Display Rotated " + String.valueOf(rotateCount) + " Times");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(NATES_TAG, "onStart method called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(NATES_TAG, "onResume method called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(NATES_TAG, "onPause method called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(NATES_TAG, "onStop method called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(NATES_TAG, "onRestart method called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(NATES_TAG, "onDestroy method called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(NATES_TAG, "onSaveInstanceState method called");
        // store values in bundle (called between onPause and onStop)
        outState.putInt(CLICK_COUNT, mClickCount);
        outState.putInt(ROTATE_COUNT, mRotateCount);
    }
}
