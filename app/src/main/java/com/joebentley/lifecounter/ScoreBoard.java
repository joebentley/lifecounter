package com.joebentley.lifecounter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {
    private static ScoreBoard mScoreBoard;

    private List<Score> mScores;

    private ScoreBoard() {
        mScores = new ArrayList<>();
        mScores.add(new Score(0, 0));
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

    public Score newCurrentScore() {
        return new Score(getCurrentScore());
    }

    public List<Score> getScoreHistory() {
        return mScores;
    }

    public void addScore(Score score) {
        mScores.add(score);
    }
}
