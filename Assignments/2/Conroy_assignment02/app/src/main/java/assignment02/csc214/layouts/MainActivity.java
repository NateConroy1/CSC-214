package assignment02.csc214.layouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button happyToast = (Button) findViewById(R.id.happy_toast_button);
        happyToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, R.string.im_happy, Toast.LENGTH_SHORT).show();
            }
        });

        Button sadToast = (Button) findViewById(R.id.sad_toast_button);
        sadToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, R.string.im_sad, Toast.LENGTH_SHORT).show();
            }
        });

        Button decrement = (Button) findViewById(R.id.decrement_button);
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView count = (TextView) findViewById(R.id.number_textview);
                String string = count.getText().toString();
                Integer value = Integer.valueOf(string);
                value = value - 1;
                string = String.valueOf(value);
                count.setText(string);
            }
        });

        Button reset = (Button) findViewById(R.id.reset_button);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView count = (TextView) findViewById(R.id.number_textview);
                count.setText(R.string.one_hundred);
            }
        });
    }
}
