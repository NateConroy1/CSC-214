package project01.csc214.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HotterColderActivity extends AppCompatActivity {

    GameView mView = GameView.getInstance();
    HotterColderGame mGame = HotterColderGame.getInstance();
    int mQuitCount;

    protected void updateView() {
        TextView player1Guesses = (TextView) findViewById(R.id.player1guesses);
        TextView player2Guesses = (TextView) findViewById(R.id.player2guesses);
        TextView hotterColderText = (TextView) findViewById(R.id.hotter_colder_text);
        EditText editText = (EditText) findViewById(R.id.hotter_colder_textedit);
        TextView player1Score = (TextView) findViewById(R.id.player1Score);
        TextView player2Score = (TextView) findViewById(R.id.player2Score);
        mView.setEditTextString(editText, "");
        mView.setTextViewString(player1Guesses, mGame.mScoreboardModel.getPlayer1Name() + getResources().getString(R.string.s_guess_count) + " " + mGame.getPlayer1guesses());
        mView.setTextViewString(player2Guesses, mGame.mScoreboardModel.getPlayer2Name() + getResources().getString(R.string.s_guess_count) + " " + mGame.getPlayer2guesses());
        if(mGame.getPlayerTurn() == HotterColderGame.PLAYER1) {
            mView.setTextViewString(hotterColderText, mGame.mScoreboardModel.getPlayer1Name() + getResources().getString(R.string.enter_a_guess_between_1_and_100));
        }
        else {
            mView.setTextViewString(hotterColderText, mGame.mScoreboardModel.getPlayer2Name() + getResources().getString(R.string.enter_a_guess_between_1_and_100));
        }
        mView.setTextViewString(player1Score, String.valueOf(mGame.mScoreboardModel.getPlayer1Score()));
        mView.setTextViewString(player2Score, String.valueOf(mGame.mScoreboardModel.getPlayer2Score()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotter_colder);

        mGame = HotterColderGame.getInstance();
        mQuitCount = 0;
        updateView();

        Button quitButton = (Button) findViewById(R.id.hotter_colder_quit_button);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuitCount++;
                if(mQuitCount == 1) {
                    Toast.makeText(HotterColderActivity.this, "Are you sure you want to quit? Click again to confirm.", Toast.LENGTH_LONG).show();
                }
                else if(mQuitCount == 2) {
                    mGame.newGame();
                    Intent intent = new Intent(HotterColderActivity.this, ChooseGameActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button newGameButton = (Button) findViewById(R.id.hotter_colder_new_game_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGame.newGame();
                updateView();
            }
        });

        Button okButton = (Button) findViewById(R.id.hotter_colder_ok);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.hotter_colder_textedit);
                String string = editText.getText().toString();
                if(!string.equals("")) {
                    int guess = Integer.valueOf(string);
                    String feedback = mGame.playTurn(guess);
                    if(isCapital(feedback)) {
                        Toast.makeText(HotterColderActivity.this, feedback, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(HotterColderActivity.this, feedback, Toast.LENGTH_LONG).show();
                    }
                    updateView();
                }
            }
        });
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
