package project01.csc214.project1;

import android.app.Activity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Nate on 2/15/17.
 */

public class GameView{

    private static GameView INSTANCE;

    private GameView(){
    }

    public static GameView getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new GameView();
        }
        return INSTANCE;
    }

    public String getEditTextString(EditText editText) {
        return editText.getText().toString();
    }

    public void setEditTextString(EditText editText, String string) {
        editText.setText(string);
    }

    public void setTextViewString(TextView textView, String string) {
        textView.setText(string);
    }

    public void makeToast(Activity activity, String string) {
        Toast.makeText(activity, string, Toast.LENGTH_LONG).show();
    }

    public void setImageViewImage(ImageView view, int id) {
        view.setImageResource(id);
    }
}
