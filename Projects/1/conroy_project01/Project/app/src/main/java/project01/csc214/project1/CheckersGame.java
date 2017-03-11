package project01.csc214.project1;

import java.util.ArrayList;

/**
 * Created by Nate on 3/10/17.
 */

public class CheckersGame {

    // Singleton model
    private static CheckersGame INSTANCE;

    private CheckersGame() {
        newGame();
    }

    public static CheckersGame getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CheckersGame();
        }
        return INSTANCE;
    }

    // declare constants
    public static final int PLAYER1 = 1;
    public static final int PLAYER2 = 2;

    public static final int EMPTY_SQUARE = 0;
    public static final int PLAYER1_CHECKER = 1;
    public static final int PLAYER2_CHECKER = 2;
    public static final int PLAYER1_KING = 3;
    public static final int PLAYER2_KING = 4;

    public static final int NUMBER_OF_ROWS = 8;
    public static final int NUMBER_OF_COLUMNS = 8;

    public int getPlayerTurn() {
        return mPlayerTurn;
    }

    private int mPlayerTurn;

    public int[][] getBoard() {
        return mBoard;
    }

    private int[][] mBoard; // a double nested array of integers to represent the board
    private boolean mGameOver;
    ScoreboardModel mScoreboardModel = ScoreboardModel.getInstance();
    int[] mCheckerToMove;
    ArrayList<Integer[]> mAvailableMoves = new ArrayList<>();

    public void newGame() {
        mPlayerTurn = PLAYER1;
        mBoard = new int[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
        mCheckerToMove = new int[]{-1,-1};
        mGameOver = false;
        // set board to initial state
        for(int i=0; i<NUMBER_OF_ROWS; i++) {
            for(int j=0; j<NUMBER_OF_COLUMNS; j++) {
                if(i < 3) {
                    if((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
                        mBoard[i][j] = PLAYER2_CHECKER;
                    }
                }
                if(i > 4) {
                    if((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
                        mBoard[i][j] = PLAYER1_CHECKER;
                    }
                }
            }
        }
    }

    public boolean checkWin() {
        for(int i=0; i<NUMBER_OF_ROWS; i++) {
            for(int j=0; j < NUMBER_OF_COLUMNS; j++) {
                if(mPlayerTurn == PLAYER1) {
                    if(mBoard[i][j] == PLAYER2_CHECKER || mBoard[i][j] == PLAYER2_KING) {
                        return false;
                    }
                }
                else {
                    if(mBoard[i][j] == PLAYER1_CHECKER || mBoard[i][j] == PLAYER1_KING) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public String play(int row, int col) {
        if(!mGameOver) {
            if (mBoard[row][col] == mPlayerTurn || mBoard[row][col] == mPlayerTurn + 2) {
                if (mBoard[row][col] == mPlayerTurn || mBoard[row][col] == mPlayerTurn + 2) {
                    setCheckerToMove(row, col);
                }
                return null;
            } else {
                if (checkerToMoveSpecified()) {
                    setAvailableMoves(mCheckerToMove[0], mCheckerToMove[1]);
                    if (inAvailableMoves(row, col)) {
                        mBoard[row][col] = mBoard[mCheckerToMove[0]][mCheckerToMove[1]];
                        mBoard[mCheckerToMove[0]][mCheckerToMove[1]] = EMPTY_SQUARE;
                        // if jumped, remove jumped chip
                        if (Math.abs(mCheckerToMove[0] - row) > 1) {
                            mBoard[(row + mCheckerToMove[0]) / 2][(col + mCheckerToMove[1]) / 2] = EMPTY_SQUARE;
                        }
                        // king the piece if it reached the other side of the board and isn't already a king
                        if (mBoard[row][col] < 3 && ((mPlayerTurn == PLAYER1 && row == 0) || (mPlayerTurn == PLAYER2 && row == NUMBER_OF_ROWS - 1))) {
                            mBoard[row][col] = mBoard[row][col] + 2;
                        }

                        if (checkWin()) {
                            mGameOver = true;
                            if (mPlayerTurn == PLAYER1) {
                                mScoreboardModel.setPlayer1Score(mScoreboardModel.getPlayer1Score() + 300);
                                return mScoreboardModel.getPlayer1Name() + " wins! +300 points.";
                            } else {
                                mScoreboardModel.setPlayer2Score(mScoreboardModel.getPlayer2Score() + 300);
                                return mScoreboardModel.getPlayer2Name() + " wins! +300 points.";
                            }
                        }

                        mAvailableMoves.clear();
                        resetCheckerToMove();
                        // change player turn
                        if (mPlayerTurn == PLAYER1) {
                            mPlayerTurn = PLAYER2;
                        } else {
                            mPlayerTurn = PLAYER1;
                        }
                    } else {
                        return "INVALID LOCATION";
                    }
                }
            }
            return null;
        }
        else {
            return "Start a new game!";
        }
    }

    public void setAvailableMoves(int row, int col) {
        if (mBoard[row][col] == PLAYER1_CHECKER || mBoard[row][col] == PLAYER1_KING) {
            if(row != 0 && col != 0) {
                if (mBoard[row - 1][col - 1] == EMPTY_SQUARE) {
                    mAvailableMoves.add(new Integer[]{row - 1, col - 1});
                }
                else if(mBoard[row - 1][col - 1] == PLAYER2_CHECKER || mBoard[row - 1][col - 1] == PLAYER2_KING) {
                    if(row != 1 && col != 1 && mBoard[row - 2][col - 2] == EMPTY_SQUARE) {
                        mAvailableMoves.add(new Integer[]{row - 2, col - 2});
                    }
                }
            }
            if(row != 0 && col != NUMBER_OF_COLUMNS - 1) {
                if (mBoard[row - 1][col + 1] == EMPTY_SQUARE) {
                    mAvailableMoves.add(new Integer[]{row - 1, col + 1});
                }
                else if(mBoard[row - 1][col + 1] == PLAYER2_CHECKER || mBoard[row - 1][col + 1] == PLAYER2_KING) {
                    if(row != 1 && col != NUMBER_OF_COLUMNS-2 && mBoard[row - 2][col + 2] == EMPTY_SQUARE) {
                        mAvailableMoves.add(new Integer[]{row - 2, col + 2});
                    }
                }
            }
            if(mBoard[row][col] == PLAYER1_KING) {
                if(row != NUMBER_OF_ROWS-1 && col != NUMBER_OF_COLUMNS-1) {
                    if (mBoard[row + 1][col + 1] == EMPTY_SQUARE) {
                        mAvailableMoves.add(new Integer[]{row + 1, col + 1});
                    }
                    else if(mBoard[row + 1][col + 1] == PLAYER2_CHECKER || mBoard[row + 1][col + 1] == PLAYER2_KING) {
                        if(row != NUMBER_OF_ROWS-2 && col != NUMBER_OF_COLUMNS-2 && mBoard[row + 2][col + 2] == EMPTY_SQUARE) {
                            mAvailableMoves.add(new Integer[]{row + 2, col + 2});
                        }
                    }
                }
                if(row != NUMBER_OF_ROWS-1 && col != 0) {
                    if (mBoard[row + 1][col - 1] == EMPTY_SQUARE) {
                        mAvailableMoves.add(new Integer[]{row + 1, col - 1});
                    }
                    else if(mBoard[row + 1][col - 1] == PLAYER2_CHECKER || mBoard[row + 1][col - 1] == PLAYER2_KING) {
                        if(row != NUMBER_OF_ROWS-2 && col != 1 && mBoard[row + 2][col - 2] == EMPTY_SQUARE) {
                            mAvailableMoves.add(new Integer[]{row + 2, col - 2});
                        }
                    }
                }
            }
        }
        else {
            if(row != NUMBER_OF_ROWS-1 && col != NUMBER_OF_COLUMNS-1) {
                if (mBoard[row + 1][col + 1] == EMPTY_SQUARE) {
                    mAvailableMoves.add(new Integer[]{row + 1, col + 1});
                }
                else if(mBoard[row + 1][col + 1] == PLAYER1_CHECKER || mBoard[row + 1][col + 1] == PLAYER1_KING) {
                    if(row != NUMBER_OF_ROWS - 2 && col != NUMBER_OF_COLUMNS-2 && mBoard[row + 2][col + 2] == EMPTY_SQUARE) {
                        mAvailableMoves.add(new Integer[]{row + 2, col + 2});
                    }
                }
            }
            if(row != NUMBER_OF_ROWS-1 && col != 0) {
                if (mBoard[row + 1][col - 1] == EMPTY_SQUARE) {
                    mAvailableMoves.add(new Integer[]{row + 1, col - 1});
                }
                else if(mBoard[row + 1][col - 1] == PLAYER1_CHECKER || mBoard[row + 1][col - 1] == PLAYER1_KING) {
                    if(row != NUMBER_OF_ROWS-2 && col != 1 && mBoard[row + 2][col - 2] == EMPTY_SQUARE) {
                        mAvailableMoves.add(new Integer[]{row + 2, col - 2});
                    }
                }
            }
            if(mBoard[row][col] == PLAYER2_KING) {
                if(row != 0 && col != 0) {
                    if (mBoard[row - 1][col - 1] == EMPTY_SQUARE) {
                        mAvailableMoves.add(new Integer[]{row - 1, col - 1});
                    }
                    else if(mBoard[row - 1][col - 1] == PLAYER1_CHECKER || mBoard[row - 1][col - 1] == PLAYER1_KING) {
                        if(row != 1 && col != 1 && mBoard[row - 2][col - 2] == EMPTY_SQUARE) {
                            mAvailableMoves.add(new Integer[]{row - 2, col - 2});
                        }
                    }
                }
                if(row != 0 && col != NUMBER_OF_COLUMNS - 1) {
                    if (mBoard[row - 1][col + 1] == EMPTY_SQUARE) {
                        mAvailableMoves.add(new Integer[]{row - 1, col + 1});
                    }
                    else if(mBoard[row - 1][col + 1] == PLAYER1_CHECKER || mBoard[row - 1][col + 1] == PLAYER1_KING) {
                        if(row != 1 && col != NUMBER_OF_COLUMNS-2 && mBoard[row - 2][col + 2] == EMPTY_SQUARE) {
                            mAvailableMoves.add(new Integer[]{row - 2, col + 2});
                        }
                    }
                }
            }
        }
    }

    public boolean inAvailableMoves(int row, int col) {
        for (Integer[] array:mAvailableMoves) {
            if(array[0] == row && array[1] == col) {
                return true;
            }
        }
        return false;
    }

    public boolean checkerToMoveSpecified() {
        if(mCheckerToMove[0] == -1) {
            return false;
        }
        return true;
    }

    public void setCheckerToMove(int i, int j) {
        mCheckerToMove[0] = i;
        mCheckerToMove[1] = j;
    }

    public void resetCheckerToMove() {
        mCheckerToMove[0] = -1;
        mCheckerToMove[1] = -1;
    }

    public void printBoard() {
        for(int i=0; i<NUMBER_OF_ROWS; i++) {
            for(int j=0; j<NUMBER_OF_COLUMNS; j++) {
                System.out.print(mBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CheckersGame test = CheckersGame.getInstance();

        test.play(5,4);
        test.play(4,3);

        test.play(2,3);
        test.play(3,2);

        test.play(5,6);
        test.play(4,7);

        test.play(3,2);
        test.play(4,3);
        test.play(5,4);

        test.play(6,5);
        test.play(5,6);

        test.play(2,1);
        test.play(3,2);

        test.play(7,6);
        test.play(6,5);

        test.play(5,4);
        test.play(7,6);

        test.play(4,7);
        test.play(3,6);

        test.play(3,2);
        test.play(2,1);

        test.printBoard();
    }
}
