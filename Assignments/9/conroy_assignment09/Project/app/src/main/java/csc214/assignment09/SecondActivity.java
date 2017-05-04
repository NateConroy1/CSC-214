package csc214.assignment09;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    EditText mEditText;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mEditText = (EditText) findViewById(R.id.async_tasks_edit_text);
        mTextView = (TextView) findViewById(R.id.async_tasks_textview);

        Button calculatePrimeNumber = (Button) findViewById(R.id.calculate_largest_prime_button);
        calculatePrimeNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // execute asynchronous task
                new GetPrimeNumberTask().execute(mEditText.getText().toString());
            }
        });

        Button calculateSquareRoot = (Button) findViewById(R.id.calculate_square_root_button);
        calculateSquareRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // execute asynchronous task
                new GetSquareRootTask().execute(mEditText.getText().toString());
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
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
                handled = true;
                break;
            default:
                handled = super.onOptionsItemSelected(item);
        }
        return handled;
    }

    private class GetSquareRootTask extends AsyncTask<String, Void, String> {

        // get square root and return string result
        @Override
        protected String doInBackground(String... nums) {
            String result = "";
            String input = nums[0];
            if(!input.equals("")) {
                result = String.valueOf(Calculator.calculateSquareRoot(Long.valueOf(input)));
            }
            return result;
        }

        // display the result after task completes
        @Override
        protected void onPostExecute(String s) {
            mTextView.setText(s);
            Toast.makeText(getApplicationContext(), "Square Root", Toast.LENGTH_SHORT).show();
        }
    }

    private class GetPrimeNumberTask extends AsyncTask<String, Void, String> {

        // get prime number and return string result
        @Override
        protected String doInBackground(String... nums) {
            String result = "";
            String input = nums[0];
            if(!input.equals("")) {
                result = String.valueOf(Calculator.calculateLargestPrimeNumber(Long.valueOf(input)));
            }
            return result;
        }

        // display the result after task completes
        @Override
        protected void onPostExecute(String s) {
            mTextView.setText(s);
            Toast.makeText(getApplicationContext(), "Prime Number", Toast.LENGTH_SHORT).show();
        }
    }
}
