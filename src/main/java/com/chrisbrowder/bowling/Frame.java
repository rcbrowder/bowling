package com.chrisbrowder.bowling;

import lombok.Data;

//TODO: Remove unnecessary getters/setters
@Data
public class Frame {
    protected Integer firstRoll;
    protected Integer secondRoll;
    protected boolean closed = false;
    protected Integer score = 0;
    protected Integer currentGameScore;

    /**
     * Sets new Frame state following roll.
     * @param pins Number of pins knocked down on roll.
     */
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

    /**
     * Determines if a spare has been achieved in a Frame.
     * @return isSpare boolean.
     */
    public boolean isSpare() {
        if (firstRoll != null && secondRoll != null) {
            return 10 == firstRoll + secondRoll;
        }
        return false;
    }

    /**
     * Determines if a strike has been achieved in a Frame.
     * @return isStrike boolean.
     */
    public boolean isStrike() {
        return firstRoll == 10;
    }

    /**
     * Adds bonus pins to score in the case of a spare or strike.
     * @param pins Number of pins knocked down on roll.
     */
    public void addBonusPinsToScore(int pins) {
        score += pins;
        currentGameScore += pins;
    }

    public Integer getThirdRoll() {
        return null;
    }
}
