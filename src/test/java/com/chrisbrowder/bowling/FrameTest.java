package com.chrisbrowder.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FrameTest {
    private Frame frame;

    @BeforeEach
    public void frameInitialization() {
        frame = new Frame();
    }

    @Test
    @DisplayName("Test First Roll")
    public void testFirstRoll() {
        frame.roll(1);
        assertEquals(1, frame.getFirstRoll());
    }

    @Test
    @DisplayName("Test Score on First Roll")
    public void testScoreOnFirstRoll() {
        frame.roll(1);
       assertEquals(1, frame.getScore(), "Score");

    }

    @Test
    @DisplayName("Test Second Roll")
    public void testSecondRoll() {
        frame.roll(0);
        frame.roll(1);
        assertAll(
                () -> assertEquals(0, frame.getFirstRoll()),
                () -> assertEquals(1, frame.getSecondRoll())
        );
    }

    @Test
    @DisplayName("Test Score on Second Roll")
    public void testScoreOnSecondRoll() {
        frame.roll(1);
        frame.roll(1);
        assertEquals(2, frame.getScore());
    }

    @Test
    @DisplayName("Test Spare Check")
    public void testIsSpare() {
        frame.roll(9);
        frame.roll(1);
        assertTrue(frame.isSpare());

        frame = new Frame();
        frame.roll(0);
        frame.roll(0);
        assertFalse(frame.isSpare());
    }

    @Test
    @DisplayName("Test Spare Calculations")
    public void testCalculateSpare() {
        frame.roll(0);
        frame.roll(10);
        frame.setCurrentGameScore(20);
        frame.addBonusPinsToScore(5);
        assertAll(
                () -> assertEquals(15, frame.getScore()),
                () -> assertEquals(25, frame.getCurrentGameScore())
        );
    }

    @Test
    @DisplayName("Test Strike Calculations")
    public void testCalculateStrike() {
        frame.roll(10);
        frame.setCurrentGameScore(20);
        frame.addBonusPinsToScore(5);
        assertAll(
                () -> assertEquals(15, frame.getScore()),
                () -> assertEquals(25, frame.getCurrentGameScore())
        );
    }
}
