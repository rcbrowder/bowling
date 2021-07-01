package com.chrisbrowder.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FinalFrameTest {
    private FinalFrame finalFrame;

    @BeforeEach
    public void frameInitialization() {
        finalFrame = new FinalFrame();
    }

    @Test
    @DisplayName("Test First Roll")
    public void testFirstRoll() {
        finalFrame.roll(1);
        assertEquals(1, finalFrame.getFirstRoll());
    }

    @Test
    @DisplayName("Test Score on First Roll")
    public void testScoreOnFirstRoll() {
        finalFrame.roll(1);
        assertEquals(1, finalFrame.getScore());
    }

    @Test
    @DisplayName("Test Second Roll")
    public void testSecondRoll() {
        finalFrame.roll(0);
        finalFrame.roll(1);
        assertAll(
                () -> assertEquals(0, finalFrame.getFirstRoll()),
                () -> assertEquals(1, finalFrame.getSecondRoll())
        );
    }

    @Test
    @DisplayName("Test Score on Second Roll")
    public void testScoreOnSecondRoll() {
        finalFrame.roll(1);
        finalFrame.roll(1);
        assertEquals(2, finalFrame.getScore());
    }

    @Test
    @DisplayName("Test Third Roll")
    public void testThirdRoll() {
        finalFrame.roll(0);
        finalFrame.roll(10);
        finalFrame.roll(1);
        assertAll(
                () -> assertEquals(0, finalFrame.getFirstRoll()),
                () -> assertEquals(10, finalFrame.getSecondRoll()),
                () -> assertEquals(1, finalFrame.getThirdRoll())
        );
    }

    @Test
    @DisplayName("Test Score on Third Roll")
    public void testScoreOnThirdRoll() {
        finalFrame.roll(0);
        finalFrame.roll(9);
        finalFrame.roll(1);
        assertEquals(10, finalFrame.getScore());
    }

    @Test
    @DisplayName("Test Spare")
    public void testSpare() {
        finalFrame.roll(9);
        finalFrame.roll(1);
        finalFrame.roll(9);

        assertEquals(19, finalFrame.getScore());
    }

    @Test
    @DisplayName("Test Three Strikes")
    public void testThreeStrikes() {
        finalFrame.roll(10);
        finalFrame.roll(10);
        finalFrame.roll(10);

        assertEquals(50, finalFrame.getScore());
    }
}
