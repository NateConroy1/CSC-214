package csc214.assignment09;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FourthActivity extends AppCompatActivity {

    ImageView mImageView;
    Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        mImageView = (ImageView) findViewById(R.id.image_url_imageview);

        // set button on click listener
        mSubmitButton = (Button) findViewById(R.id.image_url_submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // disable button after clicked
                mSubmitButton.setEnabled(false);
                // execute asynchronous task with inputted URL as an argument
                EditText editText = (EditText) findViewById(R.id.image_url_edit_text);
                new GetUrlTask().execute(editText.getText().toString());
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
                Intent intent = new Intent(FourthActivity.this, MainActivity.class);
                startActivity(intent);
                handled = true;
                break;
            default:
                handled = super.onOptionsItemSelected(item);
        }
        return handled;
    }


    private class GetUrlTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                // get image from URL
                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(params[0]).getContent());
                // set ImageView from main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mImageView.setImageBitmap(bitmap);
                    }
                });
                return "Success!";
            }
            catch (IOException e) {
                return "IOException in GetURLTask: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String string) {
            Toast.makeText(getApplicationContext(), string, Toast.LENGTH_LONG).show();
            // re-enable button
            mSubmitButton.setEnabled(true);
        }
    }
}
