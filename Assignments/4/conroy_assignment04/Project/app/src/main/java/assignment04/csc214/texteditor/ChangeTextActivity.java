package assignment04.csc214.texteditor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChangeTextActivity extends AppCompatActivity {

    FontModel mModel;
    FontView mView;

    EditText mMessageEditText;
    TextView mMainTextView;

    Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_text);

        mModel = FontModel.getInstance();
        mView = FontView.getInstance();

        Button changeTextOkButton = (Button) findViewById(R.id.changeTextOkButton);
        Button changeTextCancelButton = (Button) findViewById(R.id.changeTextCancelButton);

        mMessageEditText = (EditText) findViewById(R.id.message);
        mMainTextView = (TextView) findViewById(R.id.mainTextView);

        // add fragment
        mFragment = new PreviewFragment();
        firstFragment(findViewById(R.id.fragment2_frame));

        // on CANCEL
        changeTextCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go back to main activity
                Intent intent = new Intent(ChangeTextActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // on OK
        changeTextOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // update message in model
                String message = mView.getEditTextString(mMessageEditText);
                if(!message.equals("")) {
                    mModel.setSentence(message);
                }

                // go back to main activity
                Intent intent = new Intent(ChangeTextActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // add text changed listener to EditText to update preview as user types
        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String string = mMessageEditText.getText().toString();
                TextView textView = (TextView) findViewById(R.id.preview_textview);
                textView.setText(string);
                firstFragment(findViewById(R.id.fragment2_frame));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void firstFragment(View view) {
        if(mFragment.isAdded() == false) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment2_frame, mFragment)
                    .commit();
        }
    }
}
