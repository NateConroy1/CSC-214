package project01.csc214.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChooseGameActivity extends AppCompatActivity {

    ScoreboardModel mScoreboardModel = ScoreboardModel.getInstance();
    GameView mGameView = GameView.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);

        Button backButton = (Button) findViewById(R.id.choose_game_back_button);
        Button resetScoresButton = (Button) findViewById(R.id.reset_scores_button);
        Button hotterColderButton = (Button) findViewById(R.id.launch_hotter_colder_button);
        Button hangmanButton = (Button) findViewById(R.id.launch_hangman_button);
        Button connect4Button = (Button) findViewById(R.id.launch_connect4_button);
        Button checkersButton = (Button) findViewById(R.id.launch_checkers_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChooseGameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        resetScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mScoreboardModel.setPlayer1Score(0);
                mScoreboardModel.setPlayer2Score(0);
                TextView textView = (TextView) findViewById(R.id.player1Score);
                mGameView.setTextViewString(textView, "0");
                textView = (TextView) findViewById(R.id.player2Score);
                mGameView.setTextViewString(textView, "0");
            }
        });

        hotterColderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseGameActivity.this, HotterColderActivity.class);
                startActivity(intent);
            }
        });

        hangmanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(ChooseGameActivity.this, HangmanActivity.class);
                startActivity(intent);
            }
        });

        connect4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseGameActivity.this, Connect4Activity.class);
                startActivity(intent);
            }
        });

        checkersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseGameActivity.this, CheckersActivity.class);
                startActivity(intent);
            }
        });
    }
}
