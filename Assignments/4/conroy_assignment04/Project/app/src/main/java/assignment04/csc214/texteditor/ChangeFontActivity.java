package assignment04.csc214.texteditor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class ChangeFontActivity extends AppCompatActivity {

    CheckBox mCheckBoxBold;
    CheckBox mCheckBoxItalic;
    CheckBox mCheckBoxUnderline;

    FontModel mModel;
    FontView mView;

    RadioButton mRadioButtonBlack;
    RadioButton mRadioButtonRed;
    RadioButton mRadioButtonBlue;
    RadioButton mRadioButtonGreen;

    EditText mFontSizeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_font);

        // get model and view
        mModel = FontModel.getInstance();
        mView = FontView.getInstance();

        // get check boxes
        mCheckBoxBold = (CheckBox) findViewById(R.id.boldCheckBox);
        mCheckBoxItalic = (CheckBox) findViewById(R.id.italicCheckBox);
        mCheckBoxUnderline = (CheckBox) findViewById(R.id.underlineCheckBox);

        // get radio buttons
        mRadioButtonBlack = (RadioButton) findViewById(R.id.blackRadioButton);
        mRadioButtonRed = (RadioButton) findViewById(R.id.redRadioButton);
        mRadioButtonBlue = (RadioButton) findViewById(R.id.blueRadioButton);
        mRadioButtonGreen = (RadioButton) findViewById(R.id.greenRadioButton);

        // get EditText
        mFontSizeEditText = (EditText) findViewById(R.id.sizeTextEdit);

        // set checked fields
        if(mModel.isBold()) {
            mView.setCheckedBox(mCheckBoxBold);
        }
        if(mModel.isItalic()) {
            mView.setCheckedBox(mCheckBoxItalic);
        }
        if(mModel.isUnderline()) {
            mView.setCheckedBox(mCheckBoxUnderline);
        }

        // set radio buttons
        if(mModel.getFontColor().equals("black")) {
            mView.toggleRadioButton(mRadioButtonBlack);
        }
        else if(mModel.getFontColor().equals("red")) {
            mView.toggleRadioButton(mRadioButtonRed);
        }
        else if(mModel.getFontColor().equals("blue")) {
            mView.toggleRadioButton(mRadioButtonBlue);
        }
        else if(mModel.getFontColor().equals("green")) {
            mView.toggleRadioButton(mRadioButtonGreen);
        }

        // set EditText
        mView.setEditTextString(mFontSizeEditText, String.valueOf(mModel.getFontSize()));

        Button changeFontOkButton = (Button) findViewById(R.id.changeFontOkButton);
        Button changeFontCancelButton = (Button) findViewById(R.id.changeFontCancelButton);

        // on CANCEL
        changeFontCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangeFontActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // on OK
        changeFontOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // save new style values
                if(mCheckBoxBold.isChecked()) {
                    mModel.setBold(true);
                }
                else {
                    mModel.setBold(false);
                }
                if(mCheckBoxItalic.isChecked()) {
                    mModel.setItalic(true);
                }
                else {
                    mModel.setItalic(false);
                }
                if(mCheckBoxUnderline.isChecked()) {
                    mModel.setUnderline(true);
                }
                else {
                    mModel.setUnderline(false);
                }

                // save new font color
                if(mRadioButtonBlack.isChecked()) {
                    mModel.setFontColor("black");
                }
                else if(mRadioButtonRed.isChecked()) {
                    mModel.setFontColor("red");
                }
                else if(mRadioButtonBlue.isChecked()) {
                    mModel.setFontColor("blue");
                }
                else if(mRadioButtonGreen.isChecked()) {
                    mModel.setFontColor("green");
                }

                // save new font size
                String size = mView.getEditTextString(mFontSizeEditText);
                if(!size.equals("")){
                    mModel.setFontSize(Integer.valueOf(size));
                }

                Intent intent = new Intent(ChangeFontActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
