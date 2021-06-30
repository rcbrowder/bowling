package com.chrisbrowder.bowling;

import lombok.Getter;
@Getter
public class Frame {
    private Integer firstRoll;
    private Integer secondRoll;
    private Integer score = 0;
    private Integer currentGameScore;
    private boolean closed = false;

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
