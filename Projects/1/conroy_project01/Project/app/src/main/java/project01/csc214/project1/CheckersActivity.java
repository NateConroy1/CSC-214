package project01.csc214.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CheckersActivity extends AppCompatActivity {

    private int mQuitCount;
    private CheckersGame mGame;
    private GameView mView;
    private ImageButton[][] mBoardButtons;

    public void updateView() {
        TextView prompt = (TextView) findViewById(R.id.checkers_prompt);
        TextView player1Score = (TextView) findViewById(R.id.player1Score);
        TextView player2Score = (TextView) findViewById(R.id.player2Score);
        mView.setTextViewString(player1Score, String.valueOf(mGame.mScoreboardModel.getPlayer1Score()));
        mView.setTextViewString(player2Score, String.valueOf(mGame.mScoreboardModel.getPlayer2Score()));
        if(mGame.getPlayerTurn() == CheckersGame.PLAYER1) {
            mView.setTextViewString(prompt, mGame.mScoreboardModel.getPlayer1Name() + getResources().getString(R.string.it_s_your_turn) + " Your chips are black.");
        }
        else {
            mView.setTextViewString(prompt, mGame.mScoreboardModel.getPlayer2Name() + getResources().getString(R.string.it_s_your_turn) + " Your chips are red.");
        }

        for(int i = 0; i < Connect4Game.NUMBER_OF_ROWS; i++) {
            for(int j = 0; j < Connect4Game.NUMBER_OF_COLUMNS; j++) {
                if(mGame.getBoard()[i][j] == CheckersGame.PLAYER1_CHECKER) {
                    mView.setImageButtonImage(mBoardButtons[i][j], R.drawable.checker_black);
                }
                else if(mGame.getBoard()[i][j] == CheckersGame.PLAYER2_CHECKER) {
                    mView.setImageButtonImage(mBoardButtons[i][j], R.drawable.checker_red);
                }
                else if(mGame.getBoard()[i][j] == CheckersGame.PLAYER1_KING) {
                    mView.setImageButtonImage(mBoardButtons[i][j], R.drawable.checker_black_king);
                }
                else if(mGame.getBoard()[i][j] == CheckersGame.PLAYER2_KING) {
                    mView.setImageButtonImage(mBoardButtons[i][j], R.drawable.checker_red_king);
                }
                else {
                    mView.setImageButtonImage(mBoardButtons[i][j], null);
                }
            }
        }
    }

    private ImageButton[][] populateBoardButtonsArray() {
        ImageButton[][] array = new ImageButton[Connect4Game.NUMBER_OF_ROWS][Connect4Game.NUMBER_OF_COLUMNS];
        for(int i = 0; i < CheckersGame.NUMBER_OF_ROWS; i++) {
            for(int j = 0; j < CheckersGame.NUMBER_OF_COLUMNS; j++) {
                String string = "checkerboard_" + i + "_" + j;
                int id = getResources().getIdentifier(string, "id", getPackageName());
                array[i][j] = (ImageButton) findViewById(id);
            }
        }
        return array;
    }

    public boolean isCapital(String string) {
        for(int i=0; i<string.length(); i++) {
            if(!Character.isUpperCase(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkers);

        mGame = CheckersGame.getInstance(); // model
        mView = GameView.getInstance(); // view
        mQuitCount = 0;

        mBoardButtons = populateBoardButtonsArray();

        // set ImageButton onClickListeners
        for(int i=0; i < CheckersGame.NUMBER_OF_ROWS; i++) {
            for(int j=0; j < CheckersGame.NUMBER_OF_COLUMNS; j++) {
                ImageButton imageButton = mBoardButtons[i][j];
                final int finalI = i;
                final int finalJ = j;
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String toast = mGame.play(finalI, finalJ);
                        if(toast != null) {
                            if (isCapital(toast)) {
                                Toast.makeText(CheckersActivity.this, toast, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(CheckersActivity.this, toast, Toast.LENGTH_LONG).show();
                            }
                        }
                        updateView();
                    }
                });
            }
        }

        updateView();

        Button quitButton = (Button) findViewById(R.id.checkers_quit_button);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuitCount++;
                if(mQuitCount == 1) {
                    Toast.makeText(CheckersActivity.this, "Are you sure you want to quit? Click again to confirm.", Toast.LENGTH_LONG).show();
                }
                else if(mQuitCount == 2) {
                    mGame.newGame();
                    Intent intent = new Intent(CheckersActivity.this, ChooseGameActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button newGameButton = (Button) findViewById(R.id.checkers_new_game_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGame.newGame();
                updateView();
            }
        });
    }
}
