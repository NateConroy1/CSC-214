package project01.csc214.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class HangmanActivity extends AppCompatActivity {

    int mQuitCount;
    HangmanGame mGame;
    GameView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);

        mGame = HangmanGame.getInstance(); // model
        mView = GameView.getInstance(); // view

        mQuitCount = 0;

        Button quitButton = (Button) findViewById(R.id.hangman_quit_button);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuitCount++;
                if(mQuitCount == 1) {
                    Toast.makeText(HangmanActivity.this, "Are you sure you want to quit? Click again to confirm.", Toast.LENGTH_LONG).show();
                }
                else if(mQuitCount == 2) {
                    mGame.newGame();
                    Intent intent = new Intent(HangmanActivity.this, ChooseGameActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button okButton = (Button) findViewById(R.id.hangman_ok);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.hangman_edittext);
                String string = editText.getText().toString();
                if(!string.equals("")) {
                    String feedback = mGame.playTurn(string.charAt(0));
                    if(feedback != null) {
                        if(isCapital(feedback)) {
                            Toast.makeText(HangmanActivity.this, feedback, Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(HangmanActivity.this, feedback, Toast.LENGTH_LONG).show();
                        }
                    }
                    updateView();
                }
            }
        });

        Button newGameButton = (Button) findViewById(R.id.hangman_new_game_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGame.newGame();
                updateView();
            }
        });

        updateView();
    }

    public void updateView() {
        TextView word = (TextView) findViewById(R.id.hangman_word);
        TextView misses = (TextView) findViewById(R.id.hangman_misses);
        TextView prompt = (TextView) findViewById(R.id.hangman_prompt);
        TextView player1Score = (TextView) findViewById(R.id.player1Score);
        TextView player2Score = (TextView) findViewById(R.id.player2Score);
        EditText editText = (EditText) findViewById(R.id.hangman_edittext);
        mView.setEditTextString(editText, "");
        mView.setTextViewString(word, mGame.getMessageBox());
        mView.setTextViewString(player1Score, String.valueOf(mGame.mScoreboardModel.getPlayer1Score()));
        mView.setTextViewString(player2Score, String.valueOf(mGame.mScoreboardModel.getPlayer2Score()));
        if(mGame.getPlayerTurn() == HangmanGame.PLAYER1) {
            mView.setTextViewString(misses, getResources().getString(R.string.misses) + " " + mGame.getPlayer1Misses() + "/8");
            mView.setTextViewString(prompt, mGame.mScoreboardModel.getPlayer1Name() + getResources().getString(R.string.enter_a_letter));
        }
        else {
            mView.setTextViewString(misses, getResources().getString(R.string.misses) + " " + mGame.getPlayer2Misses() + "/8");
            mView.setTextViewString(prompt, mGame.mScoreboardModel.getPlayer2Name() + getResources().getString(R.string.enter_a_letter));
        }
    }

    public boolean isCapital(String string) {
        for(int i=0; i<string.length(); i++) {
            if(!Character.isUpperCase(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
