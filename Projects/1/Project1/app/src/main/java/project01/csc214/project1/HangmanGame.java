package project01.csc214.project1;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nate on 3/2/17.
 */

public class HangmanGame {

    // Singleton Model
    private static HangmanGame INSTANCE;

    public static final int PLAYER1 = 1;
    public static final int PLAYER2 = 2;

    private String mWord;
    private String mMessageBox;
    private int mPlayer1Misses;
    private int mPlayer2Misses;
    private int mPlayerTurn;
    private boolean mGameOver;
    private ArrayList<Character> mGuessedLetters = new ArrayList<Character>();
    private String[] mWordBank = new String[]{"intellect","periwinkle","supernova","solstice","cathedral","autonomous","eloquent","submarine","endangered","compression","giraffe","peculiar","shoelace","extraneous","international","malicious","dichotomy","juxtapose","uranium","creation","alternative","volleyball","presence","mortgage","renegade","absolute","mixture","polymorphism","birthday","domestic"};

    ScoreboardModel mScoreboardModel = ScoreboardModel.getInstance();

    private HangmanGame() {
        newGame();
    }

    public static HangmanGame getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new HangmanGame();
        }
        return INSTANCE;
    }


    public String playTurn(char guess) {
        if(!mGameOver) {
            if(guess < 'a' || guess > 'z') {
                return "INVALID INPUT";
            }
            if(mGuessedLetters.contains(guess)) {
               return "You already guessed that letter!";
            }
            mGuessedLetters.add(guess);
            String toast = getFeedbackString(guess);

            // check if turn is over
            if(mPlayerTurn == PLAYER1) {
                if (mMessageBox.replaceAll(" |_", "").equals(mWord) || mPlayer1Misses > 7) {
                    mPlayerTurn = PLAYER2;
                    mGuessedLetters.clear();
                    String string = "_";
                    for(int i = 1; i<mWord.length(); i++) {
                        string = string + " _";
                    }
                    mMessageBox = string;
                    if(mPlayer1Misses > 7) {
                        return "Sorry you're out of guesses. Pass the device.";
                    }
                    return "You guessed it! Pass the device.";
                }
            }
            else {
                if (mMessageBox.replaceAll(" |_", "").equals(mWord) || mPlayer2Misses > 7) {
                    mGameOver = true;
                    // show answer
                    String answer = "";
                    for(int i=0; i<mWord.length()-1; i++) {
                        answer = answer + mWord.charAt(i) + " ";
                    }
                    answer = answer + mWord.charAt(mWord.length() - 1);
                    mMessageBox = answer;
                    // assign points, let users know who won
                    int points = Math.abs(mPlayer1Misses - mPlayer2Misses) * 100;
                    if(mPlayer1Misses < mPlayer2Misses) {
                        mScoreboardModel.setPlayer1Score(mScoreboardModel.getPlayer1Score() + points);
                        return mScoreboardModel.getPlayer1Name() + " wins " + points + " points!";
                    }
                    else if(mPlayer1Misses > mPlayer2Misses) {
                        mScoreboardModel.setPlayer2Score(mScoreboardModel.getPlayer2Score() + points);
                        return mScoreboardModel.getPlayer2Name() + " wins " + points + " points!";
                    }
                    else { // it's a tie
                        return "It's a tie! No points awarded.";
                    }
                }
            }
            return toast;
        }
        return "Start a new game!";
    }

    public String getFeedbackString(char guess) {
        String string = "";
        boolean miss = true;
        for(int i=0; i<mWord.length(); i++) {
            if(mGuessedLetters.contains(mWord.charAt(i))) {
                string = string + mWord.charAt(i) + " ";
                if(mWord.charAt(i) == guess) {
                    miss = false;
                }
            }
            else {
                string = string + "_ ";
            }
        }
        mMessageBox = string.substring(0,string.length() - 1);
        if(miss) {
            if(mPlayerTurn == PLAYER1) {
                mPlayer1Misses++;
            }
            else {
                mPlayer2Misses++;
            }
            return "MISS";
        }
        else {
            return null;
        }
    }

    public void newGame() {
        mPlayer1Misses = 0;
        mPlayer2Misses = 0;
        mPlayerTurn = 1;
        mGameOver = false;
        mGuessedLetters.clear();
        mWord = getRandomWord();
        String string = "_";
        for(int i = 1; i<mWord.length(); i++) {
            string = string + " _";
        }
        mMessageBox = string;
    }

    private String getRandomWord() {
        Random random = new Random();
        return mWordBank[random.nextInt(mWordBank.length)];
    }

    public String getMessageBox() {
        return this.mMessageBox;
    }

    public int getPlayerTurn() {
        return this.mPlayerTurn;
    }

    public int getPlayer1Misses() {
        return this.mPlayer1Misses;
    }

    public int getPlayer2Misses() {
        return this.mPlayer2Misses;
    }
}
