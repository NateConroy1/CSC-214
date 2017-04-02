package assignment07.csc214.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import assignment07.csc214.project.model.Runner;
import assignment07.csc214.project.model.RunnerCollection;

public class NewRunnerActivity extends AppCompatActivity {

    RunnerCollection mRunnerCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_runner);

        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
                EditText yearEditText = (EditText) findViewById(R.id.year_edit_text);
                EditText hometownEditText = (EditText) findViewById(R.id.hometown_edit_text);
                EditText eventEditText = (EditText) findViewById(R.id.event_edit_text);

                String name = nameEditText.getText().toString();
                String year = yearEditText.getText().toString();
                String hometown = hometownEditText.getText().toString();
                String event = eventEditText.getText().toString();

                Runner runner = new Runner(null);
                runner.setName(name);
                runner.setYear(year);
                runner.setHometown(hometown);
                runner.setEvent(event);

                mRunnerCollection = RunnerCollection.getInstance(getApplicationContext());
                mRunnerCollection.addRunner(runner);

                Intent intent = new Intent(NewRunnerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecyclerViewActivity();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled;
        switch(item.getItemId()) {
            case R.id.menu_add_runner:
                handled = true;
                break;
            case R.id.menu_recycler_view:
                startRecyclerViewActivity();
                handled = true;
                break;
            default:
                handled = super.onOptionsItemSelected(item);
        }
        return handled;
    }

    private void startRecyclerViewActivity() {
        Intent intent = new Intent(NewRunnerActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
