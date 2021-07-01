package com.chrisbrowder.bowling;

import lombok.Getter;
@Getter
public class Frame {
    protected Integer firstRoll;
    protected Integer secondRoll;
    protected Integer score = 0;
    protected Integer currentGameScore;
    protected boolean closed = false;

    /**
     * Sets frame state after player roll.
     *
     * @param pins Number of pins knocked down
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
        currentGameScore = firstRoll + (secondRoll != null ? secondRoll : 0);
    }
}
