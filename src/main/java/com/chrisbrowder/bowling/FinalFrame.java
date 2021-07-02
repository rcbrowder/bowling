package com.chrisbrowder.bowling;

import lombok.Getter;

public class FinalFrame extends Frame {
    @Getter private Integer thirdRoll;

    /**
     * Sets new FinalFrame state following roll.
     * @param pins Number of pins knocked down on roll.
     */
    @Override
    public void roll(int pins) {
        if (firstRoll == null) {
            firstRoll = pins;
            score = pins;
        } else if (secondRoll == null) {
            secondRoll = pins;
            score += pins;
            if (!isSpare() && !finalFrameIsStrike()) {
                closed = true;
            } else if (finalFrameIsStrike()) {
                score += pins;
            }
        } else {
            thirdRoll = pins;
            score += pins;
            if (finalFrameIsStrike()) {
                score += pins;
            }
            closed = true;
        }
    }

    /**
     * Determines if a spare has been achieved in a Frame.
     * @return isSpare boolean.
     */
    @Override
    public boolean isSpare() {
        if (firstRoll != null && secondRoll != null) return firstRoll + secondRoll == 10;
        return false;
    }

    /**
     * Determines if a strike has been achieved in a Frame.
     * @return isStrike boolean.
     */
    private boolean finalFrameIsStrike() {
        return firstRoll == 10;
    }
}
