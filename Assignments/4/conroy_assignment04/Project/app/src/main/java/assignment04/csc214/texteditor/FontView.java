package assignment04.csc214.texteditor;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Nate on 2/16/17.
 */

public class FontView {

    private static FontView INSTANCE;

    private FontView() {
    }

    public static FontView getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new FontView();
        }
        return INSTANCE;
    }

    public void setBold(TextView textView) {
        textView.setTypeface(null, Typeface.BOLD);
    }

    public void setItalic(TextView textView) {
        textView.setTypeface(null, Typeface.ITALIC);
    }

    public void setBoldItalic(TextView textView) {
        textView.setTypeface(null, Typeface.BOLD_ITALIC);
    }

    public void setUnderline(TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
    }

    public void setFontColor(TextView textView, String color) {
        if(color.equals("black")) {
            textView.setTextColor(Color.BLACK);
        }
        else if(color.equals("red")) {
            textView.setTextColor(Color.RED);
        }
        else if(color.equals("blue")) {
            textView.setTextColor(Color.BLUE);
        }
        else if(color.equals("green")) {
            textView.setTextColor(Color.GREEN);
        }
    }

    public void setTextSize(TextView textView, int size) {
        textView.setTextSize(size);
    }

    public void setTextViewString(TextView textView, String string) {
        textView.setText(string);
    }

    public boolean isBoxChecked(CheckBox checkbox) {
        return checkbox.isChecked();
    }

    public boolean isRadioChecked(RadioButton radioButton) {
        return radioButton.isChecked();
    }

    public String getEditTextString(EditText editText) {
        return editText.getText().toString();
    }

    public void setCheckedBox(CheckBox checkBox) {
        checkBox.setChecked(true);
    }

    public void toggleRadioButton(RadioButton radioButton) {
        radioButton.toggle();
    }

    public void setEditTextString(EditText editText, String string) {
        editText.setText(string);
    }
}
