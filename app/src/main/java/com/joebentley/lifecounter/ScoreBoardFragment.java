package com.joebentley.lifecounter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


public class ScoreBoardFragment extends Fragment {

    private RecyclerView mScoreRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        // Unset keep awake
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score_board, null);

        mScoreRecyclerView = (RecyclerView) view.findViewById(R.id.scoreboard_recycler_view);
        mScoreRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        ScoreBoard scoreBoard = ScoreBoard.get(getActivity());

        List<Score> scores = new ArrayList<>(scoreBoard.getScoreHistory());
        Collections.reverse(scores);

        ScoreAdapter mAdapter = new ScoreAdapter(scores);
        mScoreRecyclerView.setAdapter(mAdapter);
    }

    private class ScoreHolder extends RecyclerView.ViewHolder {
        private Score mScore;

        private TextView mDateTextView;
        private TextView mScoreTextView;

        public ScoreHolder(View itemView) {
            super(itemView);

            mDateTextView = (TextView) itemView.findViewById(R.id.score_date_textview);
            mScoreTextView = (TextView) itemView.findViewById(R.id.list_score_textview);
        }

        public void bindScore(Score score) {
            mScore = score;

            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            String time = df.format(mScore.getDate());

            mDateTextView.setText(time);
            mScoreTextView.setText(getString(R.string.list_item_score_format,
                    mScore.getPlayerOneScore(), mScore.getPlayerTwoScore()));
        }
    }

    private class ScoreAdapter extends RecyclerView.Adapter<ScoreHolder> {

        private List<Score> mScores;

        public ScoreAdapter(List<Score> scores) {
            mScores = scores;
        }

        @Override
        public ScoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_score, parent, false);
            return new ScoreHolder(view);
        }

        @Override
        public void onBindViewHolder(ScoreHolder holder, int position) {
            Score score = mScores.get(position);
            holder.bindScore(score);
        }

        @Override
        public int getItemCount() {
            return mScores.size();
        }
    }
}
