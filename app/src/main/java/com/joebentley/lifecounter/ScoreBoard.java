package com.joebentley.lifecounter;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScoreBoard {
    private static ScoreBoard mScoreBoard;

    public static final int defaultLife = 20;

    private List<Score> mScores;

    private ScoreBoard() {
        mScores = new ArrayList<>();
        mScores.add(new Score(defaultLife, defaultLife));
        duplicateCurrentScore();
    }

    public static ScoreBoard get(Context context) {
        if (mScoreBoard == null) {
            mScoreBoard = new ScoreBoard();
        }

        return mScoreBoard;
    }

    public int getCurrentScore(int nth) throws IndexOutOfBoundsException {
        return mScores.get(mScores.size() - 1).getScore(nth);
    }

    public Score getCurrentScore() {
        return mScores.get(mScores.size() - 1);
    }

    // Add a new copy of the current score to the list, giving it the new date
    public void duplicateCurrentScore() {
        Score score = cloneCurrentScore();
        score.setDate(new Date());
        mScores.add(score);
    }

    public Score cloneCurrentScore() {
        return new Score(getCurrentScore());
    }

    public List<Score> getScoreHistory() {
        return mScores;
    }

    public void addScore(Score score) {
        mScores.add(score);
    }

    public void resetScoreBoard() {
        mScores.clear();
        mScores.add(new Score(defaultLife, defaultLife));
        duplicateCurrentScore();
    }
}
