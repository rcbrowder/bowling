package com.chrisbrowder.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreboardOutputTest {
    private Scoreboard scoreboard;

    @BeforeEach
    public void scoreboardInitialization() {
        int totalFrames = 10;
        scoreboard = new Scoreboard(totalFrames);
    }

    @Test
    @DisplayName("Test Print Scoreboard")
    public void testPrintScoreboard() {
        scoreboard.roll(1);
        scoreboard.roll(9);
        scoreboard.roll(10);
        scoreboard.roll(10);
    }

    @Test
    @DisplayName("Test Invalid Input: Less than 0")
    public void testInputLessThanZero() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput  = "Illegal number of pins.\n";

        scoreboard.roll(-1);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("Test Invalid Input: Greater than 10")
    public void testInputGreaterThanTen() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput  = "Illegal number of pins.\n";

        scoreboard.roll(11);
        assertEquals(expectedOutput, outContent.toString());
    }

}
