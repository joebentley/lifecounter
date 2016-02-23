package com.joebentley.lifecounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LifeCounterFragment extends Fragment {

    private static final String DIALOG_RESET = "DialogReset";
    private static final String KEEP_AWAKE_PREF_KEY = "KeepAwake";

    private TextView mPlayerOneTextView;
    private TextView mPlayerTwoTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        // Set keep awake depending on preference
        SharedPreferences settings = getActivity().getPreferences(Context.MODE_PRIVATE);

        if (settings.getBoolean(KEEP_AWAKE_PREF_KEY, true)) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_life_counter, null);

        mPlayerOneTextView = (TextView) v.findViewById(R.id.player_one_score_textview);
        mPlayerTwoTextView = (TextView) v.findViewById(R.id.player_two_score_textview);

        Button playerOnePlusButton = (Button) v.findViewById(R.id.player_one_plus_button);
        playerOnePlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoreBoard.get(getActivity()).getCurrentScore().addToPlayerOneScore(1);
                updateScoreTextViews();
            }
        });

        Button playerOneMinusButton = (Button) v.findViewById(R.id.player_one_minus_button);
        playerOneMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoreBoard.get(getActivity()).getCurrentScore().addToPlayerOneScore(-1);
                updateScoreTextViews();
            }
        });

        Button playerTwoPlusButton = (Button) v.findViewById(R.id.player_two_plus_button);
        playerTwoPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoreBoard.get(getActivity()).getCurrentScore().addToPlayerTwoScore(1);
                updateScoreTextViews();
            }
        });

        Button playerTwoMinusButton = (Button) v.findViewById(R.id.player_two_minus_button);
        playerTwoMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoreBoard.get(getActivity()).getCurrentScore().addToPlayerTwoScore(-1);
                updateScoreTextViews();
            }
        });

        // Ending the turn duplicates the score on top of the score list, saving the current score
        Button endTurnButton = (Button) v.findViewById(R.id.end_turn_button);
        endTurnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoreBoard.get(getActivity()).duplicateCurrentScore();
                Toast.makeText(getActivity(), R.string.end_turn_toast_text, Toast.LENGTH_SHORT).show();
            }
        });

        updateScoreTextViews();

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_life_counter_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_rest_current_score:
                FragmentManager manager = getFragmentManager();
                ResetGameDialog dialog = new ResetGameDialog();
                dialog.setTargetFragment(this, 0);
                dialog.show(getFragmentManager(), DIALOG_RESET);
                return true;

            case R.id.menu_item_show_scoreboard:
                Intent intent = new Intent(getActivity(), ScoreBoardActivity.class);
                startActivity(intent);
                return true;

            case R.id.menu_item_keep_awake:
                item.setChecked(!item.isChecked());

                // Save keep awake setting
                SharedPreferences settings = getActivity().getPreferences(Context.MODE_PRIVATE);
                settings.edit().putBoolean(KEEP_AWAKE_PREF_KEY, item.isChecked()).apply();

                if (item.isChecked()) {
                    getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                } else {
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateScoreTextViews() {
        ScoreBoard scoreBoard = ScoreBoard.get(getActivity());
        mPlayerOneTextView.setText(String.valueOf(scoreBoard.getCurrentScore(0)));
        mPlayerTwoTextView.setText(String.valueOf(scoreBoard.getCurrentScore(1)));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        updateScoreTextViews();
    }
}
