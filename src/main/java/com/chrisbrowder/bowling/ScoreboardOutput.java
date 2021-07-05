package com.chrisbrowder.bowling;

import java.util.List;

public class ScoreboardOutput {
    /**
     * Prints the scoreboard.
     */
    public static void printScoreboard(List<Frame> frames) {
        int totalFrames = frames.size();
        System.out.println("_".repeat(Math.max(0, (6 * (totalFrames+1)))-2));
        String rolls = "|  ".repeat(Math.max(0, (2 * (totalFrames)))) + "|  |";
        String scores = "|     ".repeat(Math.max(0, totalFrames)) + "   |";
        for (int i = 0; i < totalFrames; i++) {
            Frame frame = frames.get(i);
            if (i != totalFrames - 1) {
                if (frame.getFirstRoll() != null) {
                    String firstRollPrintString = convertRollIntegerToPrintString(frame.getFirstRoll());
                    rolls = rolls.replaceFirst("  ", firstRollPrintString);
                }
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
                if (frame.getCurrentGameScore() != null) {
                    String scorePrintString = convertScoreIntegerToPrintString(frame.getCurrentGameScore());
                    scores = scores.replaceFirst("     ", scorePrintString);
                }
            } else {
                if (frame.getFirstRoll() != null) {
                    String firstRollPrintString = convertRollIntegerToPrintString(frame.getFirstRoll());
                    rolls = rolls.replaceFirst("  ", firstRollPrintString);
                }
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
                if (frame.getCurrentGameScore() != null) {
                    String scorePrintString = convertScoreIntegerToPrintString(frame.getCurrentGameScore());
                    scores = scores.replaceFirst("     ", scorePrintString);
                }
            }
        }
        System.out.println(rolls);
        System.out.println(scores);
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
