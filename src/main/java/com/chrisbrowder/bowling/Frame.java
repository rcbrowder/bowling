package com.chrisbrowder.bowling;

import lombok.Getter;
import lombok.Setter;

@Getter
class Frame implements IFrame{
    protected Integer firstRoll;
    protected Integer secondRoll;
    protected Integer thirdRoll;
    protected boolean closed = false;
    protected Integer score = 0;
    @Setter protected Integer currentGameScore;

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
        return firstRoll != null && firstRoll == 10;
    }

    /**
     * Adds bonus pins to score in the case of a spare or strike.
     * @param pins Number of pins knocked down on roll.
     */
    public void addBonusPinsToScore(int pins) {
        score += pins;
        currentGameScore += pins;
    }

    /**
     * Tests if user integer input is a valid roll for current frame state.
     * @param pins Number of pins knocked down on roll.
     * @return Boolean asserting if use input is valid or not.
     */
    public boolean isInputValid(int pins) {
        int pinsRemaining = firstRoll == null ? 10 : 10 - firstRoll;
        boolean inputLessThanZero = pins < 0;
        boolean rollTwoExceedsRemainingPins = pins > pinsRemaining;
        return !inputLessThanZero && !rollTwoExceedsRemainingPins;
    }
}
