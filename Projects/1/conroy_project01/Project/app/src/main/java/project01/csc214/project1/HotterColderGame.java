package project01.csc214.project1;

import java.util.Random;

/**
 * Created by Nate on 3/1/17.
 */

public class HotterColderGame {

    // Singleton model
    private static HotterColderGame INSTANCE;

    // declare constants
    public static final int PLAYER1 = 1;
    public static final int PLAYER2 = 2;

    private int mPlayer1guesses;
    private int mPlayer2guesses;
    private int mPlayerTurn;
    private boolean mGameOver;
    private int mAnswer;
    private Random mRandom;
    ScoreboardModel mScoreboardModel = ScoreboardModel.getInstance();

    private HotterColderGame() {
        mRandom = new Random();
        newGame();
    }

    public static HotterColderGame getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new HotterColderGame();
        }
        return INSTANCE;
    }

    public String playTurn(int guess) {
        if(!mGameOver) {

            if(guess < 1 || guess > 100) {
                return "INVALID INPUT";
            }

            // update guess count
            if(mPlayerTurn == PLAYER1) {
                mPlayer1guesses++;
            }
            else {
                mPlayer2guesses++;
            }

            // if user guesses correctly
            if(mAnswer == guess) {

                if (mPlayerTurn == PLAYER1) {
                    mPlayerTurn = PLAYER2;
                }
                else {
                    mGameOver = true;
                    return assignPoints();
                }
                return "You guessed correctly! Pass the device.";
            }
            else {
                return giveFeedback(guess);
            }
        }
        return "Start a new game!";
    }

    public String assignPoints() {
        int points = Math.abs(mPlayer1guesses - mPlayer2guesses) * 100;
        if(mPlayer1guesses < mPlayer2guesses) {
            mScoreboardModel.setPlayer1Score(mScoreboardModel.getPlayer1Score() + points);
            return mScoreboardModel.getPlayer1Name() + " wins " + points + " points!";
        }
        else if(mPlayer1guesses > mPlayer2guesses) {
            mScoreboardModel.setPlayer2Score(mScoreboardModel.getPlayer2Score() + points);
            return mScoreboardModel.getPlayer2Name() + " wins " + points + " points!";
        }
        else { // game ends in a tie
            return "It's a tie! No points awarded.";
        }
    }

    public String giveFeedback(int guess) {
        if(guess >= mAnswer - 3 && guess <= mAnswer + 3) {
            return "ON FIRE";
        }
        else if(guess >= mAnswer - 5 && guess <= mAnswer + 5) {
            return "BOILING";
        }
        else if(guess >= mAnswer - 10 && guess <= mAnswer + 10) {
            return "VERY HOT";
        }
        else if(guess >= mAnswer - 15 && guess <= mAnswer + 15) {
            return "HOT";
        }
        else if(guess >= mAnswer - 20 && guess <= mAnswer + 20) {
            return "WARM";
        }
        else if(guess >= mAnswer - 25 && guess <= mAnswer + 25) {
            return "COOL";
        }
        else if(guess >= mAnswer - 30 && guess <= mAnswer + 30) {
            return "COLD";
        }
        else if(guess >= mAnswer - 40 && guess <= mAnswer + 40) {
            return "VERY COLD";
        }
        else if(guess >= mAnswer - 50 && guess <= mAnswer + 50) {
            return "FREEZING";
        }
        else {
            return "ABSOLUTE ZERO";
        }
    }

    public void newGame() {
        mPlayer1guesses = 0;
        mPlayer2guesses = 0;
        mPlayerTurn = PLAYER1;
        mAnswer = mRandom.nextInt(100) + 1;
        mGameOver = false;
    }

    public int getPlayer1guesses() {
        return this.mPlayer1guesses;
    }

    public int getPlayer2guesses() {
        return this.mPlayer2guesses;
    }

    public int getPlayerTurn() {
        return this.mPlayerTurn;
    }
}
