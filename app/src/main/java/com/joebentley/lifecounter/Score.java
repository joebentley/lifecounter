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
        mScore.add(playerOneScore);
        mScore.add(playerTwoScore);
        mDate = new Date();
    }

    public Score(Score other) {
        this.mScore = new ArrayList<>(other.mScore);
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

    public Score setScore(int nth, int score) throws IndexOutOfBoundsException {
        mScore.set(nth, score);
        return this;
    }

    public Score setPlayerOneScore(int score) throws IndexOutOfBoundsException {
        mScore.set(0, score);
        return this;
    }

    public Score setPlayerTwoScore(int score) throws IndexOutOfBoundsException {
        mScore.set(1, score);
        return this;
    }

    public Score addToScore(int nth, int toadd) throws IndexOutOfBoundsException {
        mScore.set(nth, getScore(nth) + toadd);
        return this;
    }

    public Score addToPlayerOneScore(int toadd) throws IndexOutOfBoundsException {
        mScore.set(0, getPlayerOneScore() + toadd);
        return this;
    }

    public Score addToPlayerTwoScore(int toadd) throws IndexOutOfBoundsException {
        mScore.set(1, getPlayerTwoScore() + toadd);
        return this;
    }


}
