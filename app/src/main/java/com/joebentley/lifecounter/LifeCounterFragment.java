package com.joebentley.lifecounter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class LifeCounterFragment extends Fragment {

    private TextView mPlayerOneTextView;
    private TextView mPlayerTwoTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_life_counter, null);

        mPlayerOneTextView = (TextView) v.findViewById(R.id.player_one_score_textview);
        mPlayerTwoTextView = (TextView) v.findViewById(R.id.player_two_score_textview);

        Button mPlayerOnePlusButton = (Button) v.findViewById(R.id.player_one_plus_button);
        mPlayerOnePlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoreBoard scoreBoard = ScoreBoard.get(getActivity());
                scoreBoard.addScore(scoreBoard.cloneCurrentScore().addToPlayerOneScore(1));
                updateScores();
            }
        });

        Button mPlayerOneMinusButton = (Button) v.findViewById(R.id.player_one_minus_button);
        mPlayerOneMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoreBoard scoreBoard = ScoreBoard.get(getActivity());
                scoreBoard.addScore(scoreBoard.cloneCurrentScore().addToPlayerOneScore(-1));
                updateScores();
            }
        });

        Button mPlayerTwoPlusButton = (Button) v.findViewById(R.id.player_two_plus_button);
        mPlayerTwoPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoreBoard scoreBoard = ScoreBoard.get(getActivity());
                scoreBoard.addScore(scoreBoard.cloneCurrentScore().addToPlayerTwoScore(1));
                updateScores();
            }
        });

        Button mPlayerTwoMinusButton = (Button) v.findViewById(R.id.player_two_minus_button);
        mPlayerTwoMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoreBoard scoreBoard = ScoreBoard.get(getActivity());
                scoreBoard.addScore(scoreBoard.cloneCurrentScore().addToPlayerTwoScore(-1));
                updateScores();
            }
        });


        updateScores();

        return v;
    }

    private void updateScores() {
        ScoreBoard scoreBoard = ScoreBoard.get(getActivity());
        mPlayerOneTextView.setText(String.valueOf(scoreBoard.getCurrentScore(0)));
        mPlayerTwoTextView.setText(String.valueOf(scoreBoard.getCurrentScore(1)));
    }
}
