package com.joebentley.lifecounter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Score {

    private List<Integer> mScore;
    private Date mDate;

    public Score() {
        mScore = new ArrayList<>();
        mScore.add(0);
        mScore.add(0);
        mDate = new Date();
    }

    public Score(int playerOneScore, int playerTwoScore) {
        mScore = new ArrayList<>();
        mScore.add(playerOneScore, playerTwoScore);
        mDate = new Date();
    }

    public Integer getScore(int nth) throws IndexOutOfBoundsException {
        return mScore.get(nth);
    }

    public Integer getPlayerOneScore() throws IndexOutOfBoundsException {
        return mScore.get(0);
    }

    public Integer getPlayerTwoScore() throws IndexOutOfBoundsException {
        return mScore.get(1);
    }
}
