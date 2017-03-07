package project01.csc214.project1;

import java.util.ArrayList;

/**
 * Created by Nate on 3/5/17.
 */

public class Connect4Game {

    // Singleton model
    private static Connect4Game INSTANCE;

    // declare constants
    public static final int NUMBER_OF_COLUMNS = 8;
    public static final int NUMBER_OF_ROWS = 8;
    public static final int PLAYER1 = 1;
    public static final int PLAYER2 = 2;

    private int mPlayerTurn;
    private int[][] mBoard; // a double nested array of integers to represent the board
    private int[] mHighestChip; // an array to keep track of the highest chip in each column
    private boolean mGameOver;
    ScoreboardModel mScoreboardModel = ScoreboardModel.getInstance();

    private Connect4Game() {
        newGame();
    }

    public static Connect4Game getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Connect4Game();
        }
        return INSTANCE;
    }

    public void newGame() {
        mPlayerTurn = PLAYER1;
        mBoard = new int[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
        mHighestChip = new int[NUMBER_OF_COLUMNS];
        mGameOver = false;
    }

    public String dropChip(int col) {
        if(!mGameOver) {
            if (mHighestChip[col] < NUMBER_OF_ROWS) {
                int row = NUMBER_OF_ROWS - (mHighestChip[col] + 1);
                mBoard[row][col] = mPlayerTurn;
                mHighestChip[col]++;

                boolean tie = isBoardFull();
                boolean win = checkWin(row, col);
                mGameOver = tie || win;

                if(mGameOver) {
                    if(!win) {
                       return "It's a tie! No points awarded.";
                    }
                    if(mPlayerTurn == PLAYER1) {
                        mScoreboardModel.setPlayer1Score(mScoreboardModel.getPlayer1Score() + 300);
                        return mScoreboardModel.getPlayer1Name() + " wins! +300 points!";
                    }
                    mScoreboardModel.setPlayer2Score(mScoreboardModel.getPlayer2Score() + 300);
                    return mScoreboardModel.getPlayer2Name() + " wins! +300 points!";
                }

                if (mPlayerTurn == PLAYER1) {
                    mPlayerTurn = PLAYER2;
                } else {
                    mPlayerTurn = PLAYER1;
                }
            }
            else {
                return "INVALID MOVE";
            }
        }
        else {
            return "Start a new game!";
        }
        return null;
    }

    public boolean checkWin(int row, int col) {
        // arrays to represent lines that could contain possible winning combination
        ArrayList<Integer> vertical = new ArrayList<>();
        ArrayList<Integer> horizontal = new ArrayList<>();
        ArrayList<Integer> diagonal1 = new ArrayList<>();
        ArrayList<Integer> diagonal2 = new ArrayList<>();

        // populate lines that could contain a possible winning combination
        for(int i = 0; i < NUMBER_OF_ROWS; i++) {
            for(int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                if(i == row) {
                    horizontal.add(mBoard[i][j]);
                }
                if(j == col) {
                    vertical.add(mBoard[i][j]);
                }
                if((row - i) == (col - j)) {
                    diagonal1.add(mBoard[i][j]);
                }
                if((row - i) == -1 * (col - j)) {
                    diagonal2.add(mBoard[i][j]);
                }
            }
        }

        // if there are 4 chips in a row in any of these arrays, return true
        return (check4InARow(vertical) || check4InARow(horizontal) || check4InARow(diagonal1) || check4InARow(diagonal2));
    }

    public boolean check4InARow(ArrayList<Integer> line) {
        // int to keep track of the number of current player's adjacent chips
        int adjacentChipCount = 0;

        for(int i = 0; i < line.size(); i++) {
            // if chip is the current player
            if(line.get(i) == mPlayerTurn) {
                // increment chip count
                adjacentChipCount++;
                // if chip count is 4, player has won
                if(adjacentChipCount == 4) {
                    return true;
                }
            }
            // if chip is not the current player, reset the count
            else {
                adjacentChipCount = 0;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for(int i = 0; i < NUMBER_OF_ROWS; i++) {
            for(int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                if(mBoard[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] getBoard() {
        return mBoard;
    }

    public int getPlayerTurn() {
        return mPlayerTurn;
    }
}
