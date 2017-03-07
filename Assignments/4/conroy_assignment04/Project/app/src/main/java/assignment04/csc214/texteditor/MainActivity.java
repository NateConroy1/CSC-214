package assignment04.csc214.texteditor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    FontModel mModel = FontModel.getInstance();
    FontView mView = FontView.getInstance();

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.mainTextView);

        // set text
        mView.setTextViewString(mTextView, mModel.getSentence());

        // set styles
        if(mModel.isBold() && mModel.isItalic()) {
            mView.setBoldItalic(mTextView);
        }
        else if(mModel.isBold()) {
            mView.setBold(mTextView);
        }
        else if(mModel.isItalic()) {
            mView.setItalic(mTextView);
        }

        if(mModel.isUnderline()) {
            mView.setUnderline(mTextView);
        }

        // set font color
        mView.setFontColor(mTextView, mModel.getFontColor());

        // set text size
        mView.setTextSize(mTextView, mModel.getFontSize());

        // on Change Font click
        Button changeFontButton = (Button) findViewById(R.id.changeFontButton);
        changeFontButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChangeFontActivity.class);
                startActivity(intent);
            }
        });

        // on Change Text click
        Button changeTextButton = (Button) findViewById(R.id.changeTextButton);
        changeTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChangeTextActivity.class);
                startActivity(intent);
            }
        });
    }
}
