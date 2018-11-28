package jobsity.bowling.service.impl;

import jobsity.bowling.model.Frame;
import jobsity.bowling.model.LastFrame;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DefaultScoreServiceTest {

    @Test
    public void shouldGetScoreForFailedGame() {
        List<Frame> frames = getListOfFrames("F", false);

        new DefaultScoreService().setFramesScore(frames);

        assertFrames(frames, 0, 2);
    }

    @Test
    public void shouldGetScoreForZeroGame() {
        List<Frame> frames = getListOfFrames("0", false);

        new DefaultScoreService().setFramesScore(frames);

        assertFrames(frames, 0, 2);
    }

    @Test
    public void shouldGetScoreForOneGame() {
        List<Frame> frames = getListOfFrames("1", false);

        new DefaultScoreService().setFramesScore(frames);

        assertFrames(frames, 1, 2);
    }

    @Test
    public void shouldGetScoreForFiveGame() {
        List<Frame> frames = getListOfFrames("5", false);
        frames.get(frames.size() - 1).getPinfalls().add("5");

        new DefaultScoreService().setFramesScore(frames);

        assertFrames(frames, 5, 3);
    }

    @Test
    public void shouldGetScoreForPerfectGame() {
        List<Frame> frames = getListOfFrames("10", true);

        new DefaultScoreService().setFramesScore(frames);

        assertFrames(frames, 10, 3);
    }

    private List<Frame> getListOfFrames(String value, boolean perfect) {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < 9; i ++) {
            addNewFrame(value, perfect, frames, i);
        }
        addLastFrame(value, perfect, frames);
        return frames;
    }

    private void addNewFrame(String value, boolean perfect, List<Frame> frames, int i) {
        Frame frame = new Frame(value);
        if (!perfect) {
            frame.getPinfalls().add(value);
        }
        if (!frames.isEmpty()) {
            frames.get(i - 1).setNextFrame(frame);
        }
        frames.add(frame);
    }

    private void addLastFrame(String value, boolean perfect, List<Frame> frames) {
        Frame frame = new LastFrame(value);
        frame.getPinfalls().add(value);
        if (perfect) {
            frame.getPinfalls().add(value);
        }
        frames.get(8).setNextFrame(frame);
        frames.add(frame);
    }

    private void assertFrames(List<Frame> frames, int value, int multiplier) {
        int score = 0;
        for (Frame frame : frames) {
            score += value * multiplier;
            Assert.assertEquals(score, frame.getScore());
        }
    }
}
