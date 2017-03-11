package project01.csc214.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Connect4Activity extends AppCompatActivity {

    private int mQuitCount;
    private Connect4Game mGame;
    private GameView mView;
    private ImageView[][] imageViews;

    public void updateView() {
        TextView prompt = (TextView) findViewById(R.id.connect4_prompt);
        TextView player1Score = (TextView) findViewById(R.id.player1Score);
        TextView player2Score = (TextView) findViewById(R.id.player2Score);
        mView.setTextViewString(player1Score, String.valueOf(mGame.mScoreboardModel.getPlayer1Score()));
        mView.setTextViewString(player2Score, String.valueOf(mGame.mScoreboardModel.getPlayer2Score()));
        if(mGame.getPlayerTurn() == Connect4Game.PLAYER1) {
            mView.setTextViewString(prompt, mGame.mScoreboardModel.getPlayer1Name() + getResources().getString(R.string.select_a_column_to_drop_your_chip));
        }
        else {
            mView.setTextViewString(prompt, mGame.mScoreboardModel.getPlayer2Name() + getResources().getString(R.string.select_a_column_to_drop_your_chip));
        }

        for(int i = 0; i < Connect4Game.NUMBER_OF_ROWS; i++) {
            for(int j = 0; j < Connect4Game.NUMBER_OF_COLUMNS; j++) {
                if(mGame.getBoard()[i][j] == Connect4Game.PLAYER1) {
                    mView.setImageViewImage(imageViews[i][j], R.drawable.black_circle);
                }
                else if(mGame.getBoard()[i][j] == Connect4Game.PLAYER2) {
                    mView.setImageViewImage(imageViews[i][j], R.drawable.red_circle);
                }
                else {
                    mView.setImageViewImage(imageViews[i][j], R.drawable.empty_chip);
                }
            }
        }
    }

    private ImageView[][] populateImageViewsArray() {
        ImageView[][] array = new ImageView[Connect4Game.NUMBER_OF_ROWS][Connect4Game.NUMBER_OF_COLUMNS];
        for(int i = 0; i < Connect4Game.NUMBER_OF_ROWS; i++) {
            for(int j = 0; j < Connect4Game.NUMBER_OF_COLUMNS; j++) {
                String string = "connect4_slot_" + (i+1) + "_" + (j+1);
                int id = getResources().getIdentifier(string, "id", getPackageName());
                array[i][j] = (ImageView) findViewById(id);
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
        setContentView(R.layout.activity_connect4);

        mGame = Connect4Game.getInstance(); // model
        mView = GameView.getInstance(); // view
        mQuitCount = 0;

        imageViews = populateImageViewsArray();

        updateView();

        Button quitButton = (Button) findViewById(R.id.connect4_quit_button);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuitCount++;
                if(mQuitCount == 1) {
                    Toast.makeText(Connect4Activity.this, "Are you sure you want to quit? Click again to confirm.", Toast.LENGTH_LONG).show();
                }
                else if(mQuitCount == 2) {
                    mGame.newGame();
                    Intent intent = new Intent(Connect4Activity.this, ChooseGameActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button newGameButton = (Button) findViewById(R.id.connect4_new_game_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGame.newGame();
                updateView();
            }
        });

        // get column buttons
        ImageButton column1Button = (ImageButton) findViewById(R.id.connect4_column_button1);
        ImageButton column2Button = (ImageButton) findViewById(R.id.connect4_column_button2);
        ImageButton column3Button = (ImageButton) findViewById(R.id.connect4_column_button3);
        ImageButton column4Button = (ImageButton) findViewById(R.id.connect4_column_button4);
        ImageButton column5Button = (ImageButton) findViewById(R.id.connect4_column_button5);
        ImageButton column6Button = (ImageButton) findViewById(R.id.connect4_column_button6);
        ImageButton column7Button = (ImageButton) findViewById(R.id.connect4_column_button7);
        ImageButton column8Button = (ImageButton) findViewById(R.id.connect4_column_button8);

        column1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toast = mGame.dropChip(0);
                if(toast != null) {
                    if (isCapital(toast)) {
                        Toast.makeText(Connect4Activity.this, toast, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Connect4Activity.this, toast, Toast.LENGTH_LONG).show();
                    }
                }
                updateView();

            }
        });

        column2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toast = mGame.dropChip(1);
                if(toast != null) {
                    if (isCapital(toast)) {
                        Toast.makeText(Connect4Activity.this, toast, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Connect4Activity.this, toast, Toast.LENGTH_LONG).show();
                    }
                }
                updateView();
            }
        });

        column3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toast = mGame.dropChip(2);
                if(toast != null) {
                    if (isCapital(toast)) {
                        Toast.makeText(Connect4Activity.this, toast, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Connect4Activity.this, toast, Toast.LENGTH_LONG).show();
                    }
                }
                updateView();
            }
        });

        column4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toast = mGame.dropChip(3);
                if(toast != null) {
                    if (isCapital(toast)) {
                        Toast.makeText(Connect4Activity.this, toast, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Connect4Activity.this, toast, Toast.LENGTH_LONG).show();
                    }
                }
                updateView();
            }
        });

        column5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toast = mGame.dropChip(4);
                if(toast != null) {
                    if (isCapital(toast)) {
                        Toast.makeText(Connect4Activity.this, toast, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Connect4Activity.this, toast, Toast.LENGTH_LONG).show();
                    }
                }
                updateView();
            }
        });

        column6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toast = mGame.dropChip(5);
                if(toast != null) {
                    if (isCapital(toast)) {
                        Toast.makeText(Connect4Activity.this, toast, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Connect4Activity.this, toast, Toast.LENGTH_LONG).show();
                    }
                }
                updateView();
            }
        });

        column7Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toast = mGame.dropChip(6);
                if(toast != null) {
                    if (isCapital(toast)) {
                        Toast.makeText(Connect4Activity.this, toast, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Connect4Activity.this, toast, Toast.LENGTH_LONG).show();
                    }
                }
                updateView();
            }
        });

        column8Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toast = mGame.dropChip(7);
                if(toast != null) {
                    if (isCapital(toast)) {
                        Toast.makeText(Connect4Activity.this, toast, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Connect4Activity.this, toast, Toast.LENGTH_LONG).show();
                    }
                }
                updateView();
            }
        });
    }
}
