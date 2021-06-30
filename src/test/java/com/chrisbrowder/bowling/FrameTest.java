package com.chrisbrowder.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertAll(
                () -> assertEquals(1, frame.getScore()),
                () -> assertEquals(1, frame.getCurrentGameScore())
        );

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
        assertAll(
                () -> assertEquals(2, frame.getScore()),
                () -> assertEquals(2, frame.getCurrentGameScore())
        );
    }
}
