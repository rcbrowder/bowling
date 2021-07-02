package com.chrisbrowder.bowling;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Scoreboard {
    final int totalFrames;
    List<Frame> frames = new ArrayList<>();
    List<Integer> rolls = new ArrayList<>();
    private int totalGameScore = 0;
    private int currentFrameIndex = 0;

    /**
     * Scoreboard constructor. Standard game of bowling is 10 frames but this implementation leaves room for
     * non-standard game lengths.
     * @param totalFrames Total frames in the game.
     */
    public Scoreboard(int totalFrames) {
        this.totalFrames = totalFrames;
        for (int i = 0; i < totalFrames - 1; i++) {
            Frame frame = new Frame();
            frames.add(frame);
        }
        FinalFrame finalFrame = new FinalFrame();
        frames.add(finalFrame);
    }

    /**
     * This method updates the scoreboard on each roll and prints the result.
     * @param pins Number of pins knocked down on roll.
     */
    public void roll(int pins) {
        rolls.add(pins);
        Frame currentFrame = frames.get(currentFrameIndex);
        currentFrame.roll(pins);
        if (currentRollFollowsSpare(currentFrame) && currentFrame.getSecondRoll() == null) {
            frames.get(currentFrameIndex - 1).calculateSpare(pins);
            totalGameScore += pins;
        }
        if (currentRollFollowsStrike()
                && (currentFrame.getClass() != FinalFrame.class || currentFrame.getThirdRoll() != null )) {
            calculateStrikeForPreviousFrames(currentFrame, pins);
            totalGameScore += pins;
        }
        if (currentFrame.isClosed()) {
            setCurrentGameScoreForFrameAndScoreboard(currentFrame);
        }
        printScoreboard();
    }

    //TODO: Move strike calculations to frame
    private void calculateStrikeForPreviousFrames(Frame currentFrame, int pins) {
        if (currentFrameIndex > 0 && frames.get(currentFrameIndex - 1).isStrike()) {
            frames.get(currentFrameIndex - 1).calculateStrike(pins);
        }
        if (currentFrameIndex > 1 && currentFrame.getSecondRoll() == null && frames.get(currentFrameIndex - 2).isStrike()) {
            frames.get(currentFrameIndex - 2).calculateStrike(pins);
            totalGameScore += currentFrame.getScore();
        }
    }

    /**
     * Determines whether the current roll should be added to a previous frames total score due to a spare.
     * @param currentFrame Current Frame.
     */
    private boolean currentRollFollowsSpare(Frame currentFrame) {
        if (currentFrame.getClass() == FinalFrame.class && currentFrame.getThirdRoll() != null ) {
            return currentFrame.isSpare();
        }
        return currentFrameIndex > 0 && frames.get(currentFrameIndex - 1).isSpare() && currentFrame.getSecondRoll() == null;
    }


    /**
     * Determines whether the current roll should be added to a previous frames total score due to a strike.
     */
    private boolean currentRollFollowsStrike() {
        //TODO: Refactor to get rid of 'rolls' var
        return (rolls.size() > 1 && rolls.get(rolls.size() - 2) == 10) || (rolls.size() > 2 && rolls.get(rolls.size() - 3) == 10);
    }

    /**
     * Sets total score of the frame to the score up to this point in the game.
     * @param currentFrame Current Frame.
     */
    private void setCurrentGameScoreForFrameAndScoreboard(Frame currentFrame) {
        totalGameScore += currentFrame.score;
        currentFrame.setCurrentGameScore(totalGameScore);
        currentFrameIndex++;
    }

    /**
     * Prints the scoreboard.
     */
    public void printScoreboard() {
        System.out.println("_".repeat(Math.max(0, (6 * (totalFrames+1)))-2));
        String rolls = "|  ".repeat(Math.max(0, (2 * (totalFrames)))) + "|  |";
        String scores = "|     ".repeat(Math.max(0, totalFrames)) + "   |";
        for (int i = 0; i < totalFrames; i++) {
            Frame frame = frames.get(i);
            if (i != totalFrames - 1) {
                if (frame.getFirstRoll() != null) {
                    String firstRollPrintString = rollToPrintString(frame.getFirstRoll());
                    rolls = rolls.replaceFirst("  ", firstRollPrintString);
                }
                if (frame.getSecondRoll() != null || (frame.getFirstRoll() != null && frame.isStrike())) {
                    String secondRollPrintString;
                    if (frame.isSpare()) {
                        secondRollPrintString = " /";
                    } else if (frame.isStrike()) {
                        secondRollPrintString = " -";
                    } else {
                        secondRollPrintString = rollToPrintString(frame.getSecondRoll());
                    }
                    rolls = rolls.replaceFirst("  ", secondRollPrintString);
                }
                if (frame.getCurrentGameScore() != null) {
                    String scorePrintString = scoreToPrintString(frame.getCurrentGameScore());
                    scores = scores.replaceFirst("     ", scorePrintString);
                }
            } else {
                if (frame.getFirstRoll() != null) {
                    String firstRollPrintString = rollToPrintString(frame.getFirstRoll());
                    rolls = rolls.replaceFirst("  ", firstRollPrintString);
                }
                if (frame.getSecondRoll() != null) {
                    String secondRollPrintString;
                    if (frame.isSpare()) {
                        secondRollPrintString = " /";
                    } else {
                        secondRollPrintString = rollToPrintString(frame.getSecondRoll());
                    }
                    rolls = rolls.replaceFirst("  ", secondRollPrintString);
                }
                if (frame.getThirdRoll() != null) {
                    String thirdRollPrintString = rollToPrintString(frame.getThirdRoll());
                    rolls = rolls.replaceFirst("  ", thirdRollPrintString);
                }
                if (frame.getCurrentGameScore() != null) {
                    String scorePrintString = scoreToPrintString(frame.getCurrentGameScore());
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
    private String scoreToPrintString(Integer score) {
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
    private String rollToPrintString(Integer roll) {
        if (roll == 10) {
            return " X";
        } else {
            return " " + roll;
        }
    }

}


