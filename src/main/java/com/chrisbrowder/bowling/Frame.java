package com.chrisbrowder.bowling;

import lombok.Data;

@Data
public class Frame {
    protected Integer firstRoll;
    protected Integer secondRoll;
    protected boolean closed = false;
    protected Integer score = 0;
    protected Integer currentGameScore;


    public void roll(int pins) {
        if (firstRoll == null) {
            firstRoll = pins;
            score = pins;
            if (pins == 10) {
                closed = true;
            }
        } else if (!closed) {
            secondRoll = pins;
            score += pins;
            closed = true;
        }
    }

    public boolean isSpare() {
        if (firstRoll != null && secondRoll != null) {
            return 10 == firstRoll + secondRoll;
        }
        return false;
    }

    public void calculateSpare(int pins) {
        score += pins;
        currentGameScore += pins;
    }

    public void calculateStrike(int pins) {
        score += pins;
        currentGameScore += pins;
    }

    public boolean isStrike() {
        return firstRoll == 10;
    }

    public Integer getThirdRoll() {
        return null;
    }
}
