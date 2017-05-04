package csc214.assignment09;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import csc214.assignment09.handlers.PrimeHandlerThread;
import csc214.assignment09.handlers.SqrtHandlerThread;

public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "ThirdActivityTag";

    EditText mEditText;
    TextView mTextView;

    private PrimeHandlerThread mPrimeHandler;
    private SqrtHandlerThread mSqrtHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mEditText = (EditText) findViewById(R.id.async_tasks_edit_text);
        mTextView = (TextView) findViewById(R.id.async_tasks_textview);

        // create Handler
        Handler responseHandler = new Handler();
        mPrimeHandler = new PrimeHandlerThread(responseHandler);
        // set listener
        mPrimeHandler.setPrimeListener(new PrimeHandlerThread.PrimeListener() {
            @Override
            public void someWorkCompleted(String work) {
                // display result
                mTextView.setText(work);
                Toast.makeText(getApplicationContext(), "Prime Number", Toast.LENGTH_SHORT).show();
            }
        });

        // start handler and get looper
        mPrimeHandler.start();
        mPrimeHandler.getLooper();

        Log.i(TAG, "Started prime number handler");

        mSqrtHandler = new SqrtHandlerThread(responseHandler);
        // set listener
        mSqrtHandler.setSqrtListener(new SqrtHandlerThread.SqrtListener() {
            @Override
            public void someWorkCompleted(String work) {
                // display result
                mTextView.setText(work);
                Toast.makeText(getApplicationContext(), "Square Root", Toast.LENGTH_SHORT).show();
            }
        });

        // start handler and get looper
        mSqrtHandler.start();
        mSqrtHandler.getLooper();

        Log.i(TAG, "Started square root handler");

        // set button on click listeners

        Button calculatePrimeNumber = (Button) findViewById(R.id.calculate_largest_prime_button);
        calculatePrimeNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // calculate prime number
                mPrimeHandler.calculatePrime(mEditText.getText().toString());
            }
        });

        Button calculateSquareRoot = (Button) findViewById(R.id.calculate_square_root_button);
        calculateSquareRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // calculate square root
                mSqrtHandler.calculateSqrt(mEditText.getText().toString());
            }
        });
    }

    // inflate menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    // implement "back" button functionality
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled;
        switch(item.getItemId()) {
            case R.id.back_menu_item:
                Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(intent);
                handled = true;
                break;
            default:
                handled = super.onOptionsItemSelected(item);
        }
        return handled;
    }
}
