package com.chrisbrowder.bowling;

public interface IFrame {
    boolean isInputValid(int pins);
    void roll(int pins);
    boolean isSpare();
    boolean isStrike();
    boolean isClosed();
    void addBonusPinsToScore(int pins);
}
