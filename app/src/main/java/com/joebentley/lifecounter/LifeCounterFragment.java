package com.joebentley.lifecounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class LifeCounterFragment extends Fragment {

    private TextView mPlayerOneTextView;
    private TextView mPlayerTwoTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_life_counter, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_show_scoreboard:
                Intent intent = new Intent(getActivity(), ScoreBoardActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateScores() {
        ScoreBoard scoreBoard = ScoreBoard.get(getActivity());
        mPlayerOneTextView.setText(String.valueOf(scoreBoard.getCurrentScore(0)));
        mPlayerTwoTextView.setText(String.valueOf(scoreBoard.getCurrentScore(1)));
    }
}
