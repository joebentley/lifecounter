package com.joebentley.lifecounter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ScoreBoardFragment extends Fragment {

    private RecyclerView mScoreRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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

        private TextView mPlayerOneScoreTextView;
        private TextView mPlayerTwoScoreTextView;

        public ScoreHolder(View itemView) {
            super(itemView);

            mPlayerOneScoreTextView = (TextView) itemView.findViewById(R.id.player_one_list_score_textview);
            mPlayerTwoScoreTextView = (TextView) itemView.findViewById(R.id.player_two_list_score_textview);
        }

        public void bindScore(Score score) {
            mScore = score;
            mPlayerOneScoreTextView.setText(getString(R.string.player_one_score_format, mScore.getPlayerOneScore()));
            mPlayerTwoScoreTextView.setText(getString(R.string.player_two_score_format, mScore.getPlayerTwoScore()));
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
