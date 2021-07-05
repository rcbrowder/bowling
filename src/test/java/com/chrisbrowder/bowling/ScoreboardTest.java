package com.chrisbrowder.bowling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreboardTest {
    private Scoreboard scoreboard;

    @BeforeEach
    public void scoreboardInitialization() {
        int totalFrames = 10;
        scoreboard = new Scoreboard(totalFrames);
    }

    @Test
    @DisplayName("Scoreboard Size")
    public void testScoreboardSize() {
        Scoreboard largeScoreboard = new Scoreboard(20);
        assertAll(
                () -> assertEquals(10, scoreboard.getFrames().size()),
                () -> assertEquals(20, largeScoreboard.getFrames().size())
        );
    }

    @Test
    @DisplayName("Rolling All Zeros")
    public void testRollingAllZeros() {
        for (int i = 1; i <= 20; i++) {
            scoreboard.roll(0);
        }
        assertEquals(0, scoreboard.getTotalGameScore());
    }

    @Test
    @DisplayName("Rolling All Ones")
    public void testAllOnes() {
        for (int i = 1; i <= 20; i++) {
            scoreboard.roll(1);
        }
        assertEquals(20, scoreboard.getTotalGameScore());
    }

    @Test
    @DisplayName("Test Single Spare")
    public void testSingleSpare() {
        scoreboard.roll(8);
        scoreboard.roll(2);
        scoreboard.roll(1);
        scoreboard.roll(1);
        for (int i = 1; i <= 16; i++) {
            scoreboard.roll(0);
        }
        assertEquals(13, scoreboard.getTotalGameScore());
    }

    @Test
    @DisplayName("Test All Spares")
    public void testAllSpares() {
        for (int i = 1; i <= 10; i++) {
            scoreboard.roll(9);
            scoreboard.roll(1);
        }
        scoreboard.roll(9);
        assertEquals(190, scoreboard.getTotalGameScore());
    }

    @Test
    @DisplayName("Test Single Strike")
    public void testSingleStrike() {
        scoreboard.roll(10);
        scoreboard.roll(3);
        scoreboard.roll(5);
        for (int i = 1; i <= 16; i++) {
            scoreboard.roll(0);
        }
        assertEquals(26, scoreboard.getTotalGameScore());
    }

    @Test
    @DisplayName("Test Consecutive Strikes")
    public void testConsecutiveStrikes() {
        scoreboard.roll(10);
        scoreboard.roll(10);
        scoreboard.roll(4);
        scoreboard.roll(2);
        for (int i = 1; i <= 14; i++) {
            scoreboard.roll(0);
        }
        assertEquals(46, scoreboard.getTotalGameScore());
    }

    @Test
    @DisplayName("Test Perfect Game")
    public void testAllStrikes() {
        for (int i = 1; i <= 12; i++) {
            scoreboard.roll(10);
        }
        assertEquals(300, scoreboard.getTotalGameScore());
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

//    @Test
//    @DisplayName("Test Invalid Input: Greater than pins remaining")
//    public void testInputGreaterThanPinsRemaining() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        String expectedOutput  = "Illegal number of pins.\n";
//
//        scoreboard.roll(5);
//        scoreboard.roll(7);
//        assert
//        assertEquals(expectedOutput, outContent.toString());
//
//    }
}
