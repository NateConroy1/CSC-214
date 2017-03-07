package project01.csc214.project1;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentScoreboard extends Fragment {

    ScoreboardModel mScoreboardModel = ScoreboardModel.getInstance();
    GameView mGameView = GameView.getInstance();

    TextView mPlayer1ScoreboardName;
    TextView mPlayer2ScoreboardName;
    TextView mPlayer1Score;
    TextView mPlayer2Score;


    public FragmentScoreboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scoreboard, container, false);

        // get names and scores
        mPlayer1ScoreboardName = (TextView) view.findViewById(R.id.player1ScoreboardName);
        mPlayer2ScoreboardName = (TextView) view.findViewById(R.id.player2ScoreboardName);
        mPlayer1Score = (TextView) view.findViewById(R.id.player1Score);
        mPlayer2Score = (TextView) view.findViewById(R.id.player2Score);

        // display names and scores
        mGameView.setTextViewString(mPlayer1ScoreboardName, mScoreboardModel.getPlayer1Name());
        mGameView.setTextViewString(mPlayer2ScoreboardName, mScoreboardModel.getPlayer2Name());
        mGameView.setTextViewString(mPlayer1Score, String.valueOf(mScoreboardModel.getPlayer1Score()));
        mGameView.setTextViewString(mPlayer2Score, String.valueOf(mScoreboardModel.getPlayer2Score()));

        return view;
    }

}
