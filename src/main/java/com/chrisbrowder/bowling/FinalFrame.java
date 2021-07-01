package com.chrisbrowder.bowling;

import lombok.Getter;

public class FinalFrame extends Frame {
    @Getter private Integer thirdRoll;

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

    @Override
    public boolean isSpare() {
        if (firstRoll != null && secondRoll != null) return firstRoll + secondRoll == 10;
        return false;
    }

    private boolean finalFrameIsStrike() {
        return firstRoll == 10;
    }
}
