package com.chrisbrowder.bowling;

import lombok.Getter;

public class FinalFrame extends Frame {
    @Getter
    private Integer thirdRoll;

    @Override
    public void roll(int pins) {
        if (firstRoll == null) {
            firstRoll = pins;
            score = pins;
        } else if (secondRoll == null) {
            secondRoll = pins;
            score += pins;
        } else {
            thirdRoll = pins;
            score += pins;
            closed = true;
        }
        currentGameScore = firstRoll + (secondRoll != null ? secondRoll : 0) + (thirdRoll != null ? thirdRoll : 0);
    }
}
