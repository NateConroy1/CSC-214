package project01.csc214.project1;

/**
 * Created by Nate on 2/15/17.
 */

public class ScoreboardModel {
    private static ScoreboardModel INSTANCE;

    private ScoreboardModel(){
    }

    public static ScoreboardModel getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ScoreboardModel();
        }
        return INSTANCE;
    }

    // scoreboard variables
    private String player1Name = "";
    private String player2Name = "";
    private int player1Score = 0;
    private int player2Score = 0;

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

}
