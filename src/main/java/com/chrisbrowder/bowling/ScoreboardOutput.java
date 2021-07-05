package com.chrisbrowder.bowling;

import java.util.List;

public class ScoreboardOutput {
    private static String rolls;
    private static String scores;

    /**
     * Prints the scoreboard to the console in its current state.
     * @param frames List of frames from the scoreboard.
     */
    public static void printScoreboard(List<Frame> frames) {
        int totalFrames = frames.size();
        printTopLineOfScoreboard(totalFrames);
        rolls = "|  ".repeat(Math.max(0, (2 * (totalFrames)))) + "|  |";
        scores = "|     ".repeat(totalFrames) + "   |";
        for (int i = 0; i < totalFrames; i++) {
            Frame frame = frames.get(i);
            if (i != totalFrames - 1) {
                printDefaultFrame(frame);
            } else {
                printFinalFrame(frame);
            }
        }
        System.out.println(rolls);
        System.out.println(scores);
        printBottomLineOfScoreboard(totalFrames);
    }


    /**
     * Print default frame to scoreboard.
     * @param frame Current frame.
     */
    private static void printDefaultFrame(Frame frame) {
        printFirstRoll(frame);
        if (frame.getSecondRoll() != null || (frame.getFirstRoll() != null && frame.isStrike())) {
            String secondRollPrintString;
            if (frame.isSpare()) {
                secondRollPrintString = " /";
            } else if (frame.isStrike()) {
                secondRollPrintString = " -";
            } else {
                secondRollPrintString = convertRollIntegerToPrintString(frame.getSecondRoll());
            }
            rolls = rolls.replaceFirst("  ", secondRollPrintString);
        }
        printCurrentGameScore(frame);
    }

    /**
     * Print final frame to scoreboard.
     * @param frame Current frame.
     */
    private static void printFinalFrame(Frame frame) {
        printFirstRoll(frame);
        if (frame.getSecondRoll() != null) {
            String secondRollPrintString;
            if (frame.isSpare()) {
                secondRollPrintString = " /";
            } else {
                secondRollPrintString = convertRollIntegerToPrintString(frame.getSecondRoll());
            }
            rolls = rolls.replaceFirst("  ", secondRollPrintString);
        }
        if (frame.getThirdRoll() != null) {
            String thirdRollPrintString = convertRollIntegerToPrintString(frame.getThirdRoll());
            rolls = rolls.replaceFirst("  ", thirdRollPrintString);
        }
        printCurrentGameScore(frame);
    }

    /**
     * Replaces the next empty space on the scoreboard with the number of pins knocked down on the first roll.
     * @param frame Current frame.
     */
    private static void printFirstRoll(Frame frame) {
        if (frame.getFirstRoll() != null) {
            String firstRollPrintString = convertRollIntegerToPrintString(frame.getFirstRoll());
            rolls = rolls.replaceFirst("  ", firstRollPrintString);
        }
    }

    /**
     * Replaces the empty space on the "score" portion of the scoreboard with the most up to date current game score
     * for the current frame.
     * @param frame Current frame.
     */
    private static void printCurrentGameScore(Frame frame) {
        if (frame.getCurrentGameScore() != null) {
            String scorePrintString = convertScoreIntegerToPrintString(frame.getCurrentGameScore());
            scores = scores.replaceFirst("     ", scorePrintString);
        }
    }

    /**
     * Prints line of underscores for the top line of the scoreboard.
     * @param totalFrames Number of frames in the scoreboard.
     */
    private static void printTopLineOfScoreboard(int totalFrames) {
        System.out.println("_".repeat(Math.max(0, (6 * (totalFrames+1)))-2));
    }

    /**
     * Prints line of macrons for the bottom line of the scoreboard.
     * @param totalFrames Number of frames in the scoreboard.
     */
    private static void printBottomLineOfScoreboard(int totalFrames) {
        String macron = Character.toString((char)175);
        System.out.println(macron.repeat(Math.max(0, (6 * (totalFrames+1)))-2));
    }

    /**
     * Converts score Integer to print String that is centered for aesthetic purposes.
     * @param score Score being inserted into the scoreboard.
     * @return Centered score string.
     */
    private static String convertScoreIntegerToPrintString(Integer score) {
        if (score < 10) {
            return "  " + score + "  ";
        } else if (score < 100) {
            return "  " + score + " ";
        } else {
            return " " + score + " ";
        }
    }

    /**
     * Converts roll Integer to print String with score replaced by strike symbol (X) when applicable.
     * @param roll Roll being inserted into the scoreboard.
     * @return Roll string.
     */
    private static String convertRollIntegerToPrintString(Integer roll) {
        if (roll == 10) {
            return " X";
        } else {
            return " " + roll;
        }
    }
}
