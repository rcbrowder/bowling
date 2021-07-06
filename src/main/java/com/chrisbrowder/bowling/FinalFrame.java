package com.chrisbrowder.bowling;

public class FinalFrame extends Frame {

    /**
     * Sets new FinalFrame state following roll.
     * @param pins Number of pins knocked down on roll.
     */
    @Override
    public void roll(int pins) {
        if (firstRoll == null) {
            firstRoll = pins;
        } else if (secondRoll == null) {
            secondRoll = pins;
            if (!isSpare() && !isStrike()) {
                closed = true;
            } else if (isStrike()) {
                score += pins;
            }
        } else {
            thirdRoll = pins;
            if (isStrike()) {
                score += pins;
            }
            closed = true;
        }
        score += pins;
    }

    /**
     * Tests if user integer input is a valid roll for current frame state.
     * @param pins Number of pins knocked down on roll.
     * @return Boolean asserting if use input is valid or not.
     */
    @Override
    public boolean isInputValid(int pins) {
        int pinsRemaining = firstRoll == null ? 10 : 10 - firstRoll;
        boolean inputLessThanZero = pins < 0;
        boolean rollTwoExceedsRemainingPinsForFinalFrame = !isStrike() && secondRoll == null && pins > pinsRemaining;
        return !inputLessThanZero && !rollTwoExceedsRemainingPinsForFinalFrame;
    }
}
