package project01.csc214.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private ScoreboardModel mScoreboardModel;
    private GameView mGameView;

    private EditText mPlayer1EditText;
    private EditText mPlayer2EditText;

    private Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScoreboardModel = ScoreboardModel.getInstance();
        mGameView = GameView.getInstance();

        mPlayer1EditText = (EditText) findViewById(R.id.player1EditText);
        mPlayer2EditText = (EditText) findViewById(R.id.player2EditText);
        mSubmitButton = (Button) findViewById(R.id.submit_button);

        mGameView.setEditTextString(mPlayer1EditText, mScoreboardModel.getPlayer1Name());
        mGameView.setEditTextString(mPlayer2EditText, mScoreboardModel.getPlayer2Name());

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String player1name = mGameView.getEditTextString(mPlayer1EditText);
                String player2name = mGameView.getEditTextString(mPlayer2EditText);

                // check if either of the names haven't been entered
                if(player1name.equals("") || player2name.equals("")) {
                    mGameView.makeToast(MainActivity.this, "Please enter both players' names");
                }
                else { // if they both have been entered

                    // save names to model
                    mScoreboardModel.setPlayer1Name(player1name);
                    mScoreboardModel.setPlayer2Name(player2name);

                    // advance to game selection screen
                    Intent intent = new Intent(MainActivity.this, ChooseGameActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
