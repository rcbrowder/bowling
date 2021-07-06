package com.chrisbrowder.bowling;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Scoreboard {
    final int totalFrames;
    List<Frame> frames = new ArrayList<>();
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
        Frame currentFrame = frames.get(currentFrameIndex);
        if (!currentFrame.isInputValid(pins)) {
            System.out.println("Illegal number of pins.");
            return;
        }
        currentFrame.roll(pins);
        calculateBonusPinsForSpareOrStrike(currentFrame, pins);
        if (currentFrame.isClosed()) {
            setCurrentGameScoreForFrameAndScoreboard(currentFrame);
        }
        ScoreboardOutput.printScoreboard(frames);
    }

    /**
     * Adds bonus points to score to appropriate frames for spares and strikes.
     * @param currentFrame Current Frame.
     * @param pins Number of pins knocked down on roll.
     */
    private void calculateBonusPinsForSpareOrStrike(Frame currentFrame, int pins) {
        // If the current roll follows a spare and is the first roll of a frame add bonus pins
        if (currentRollFollowsSpare(currentFrame) && currentFrame.getSecondRoll() == null) {
            frames.get(currentFrameIndex - 1).addBonusPinsToScore(pins);
            totalGameScore += pins;
        }
        if (currentRollFollowsStrike(currentFrame) && (!isFinalFrame() || currentFrame.getThirdRoll() != null )) {
            calculateStrikeForPreviousTwoFrames(currentFrame, pins);
            totalGameScore += pins;
        }
    }

    /**
     * Helper function for adding bonus pins to appropriate frames after strikes.
     * @param currentFrame Current Frame.
     * @param pins Number of pins knocked down on roll.
     */
    private void calculateStrikeForPreviousTwoFrames(Frame currentFrame, int pins) {
        if (currentFrameIndex > 0 && frames.get(currentFrameIndex - 1).isStrike()) {
            frames.get(currentFrameIndex - 1).addBonusPinsToScore(pins);
        }
        if (currentFrameIndex > 1 && currentFrame.getSecondRoll() == null && frames.get(currentFrameIndex - 2).isStrike()) {
            frames.get(currentFrameIndex - 2).addBonusPinsToScore(pins);
            totalGameScore += currentFrame.getScore();
        }
    }

    /**
     * Determines whether the current roll should be added to a previous frames total score due to a spare.
     * @param currentFrame Current Frame.
     */
    private boolean currentRollFollowsSpare(Frame currentFrame) {
        if (isFinalFrame() && currentFrame.getThirdRoll() != null ) {
            return currentFrame.isSpare();
        }
        return currentFrameIndex > 0 && frames.get(currentFrameIndex - 1).isSpare() && currentFrame.getSecondRoll() == null;
    }


    /**
     * Determines whether the current roll should be added to a previous frames total score due to a strike.
     */
    private boolean currentRollFollowsStrike(Frame currentFrame) {
        boolean previousRollIsStrike = currentFrameIndex > 0 && frames.get(currentFrameIndex - 1).isStrike();
        boolean secondPreviousRollIsStrike = currentFrameIndex > 1 && frames.get(currentFrameIndex - 2).isStrike();
        if (isFinalFrame() && currentFrame.getSecondRoll() != null) {
            return currentFrame.isStrike() || previousRollIsStrike;
        }
        return previousRollIsStrike || secondPreviousRollIsStrike;
    }

    /**
     * Sets total score of the frame to the score up to this point in the game.
     * @param currentFrame Current Frame.
     */
    private void setCurrentGameScoreForFrameAndScoreboard(Frame currentFrame) {
        totalGameScore += currentFrame.getScore();
        currentFrame.setCurrentGameScore(totalGameScore);
        currentFrameIndex++;
    }

    private boolean isFinalFrame() {
        return currentFrameIndex == totalFrames - 1;
    }

}


