package jobsity.bowling.service.impl;

import jobsity.bowling.model.Frame;
import jobsity.bowling.model.LastFrame;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DefaultFrameServiceTest {

    @Test
    public void shouldGetFramesForFailedGame() {
        List<String> pins = getListOfPins(20, "F");

        List<Frame> frames = new DefaultFrameService().getFramesForPins(pins);

        assertFrames(frames, 0, 2);
        assertFrame(frames, 2, 9, 0, LastFrame.class);
    }

    @Test
    public void shouldGetFramesForZeroGame() {
        List<String> pins = getListOfPins(20, "0");

        List<Frame> frames = new DefaultFrameService().getFramesForPins(pins);

        assertFrames(frames, 0, 2);
        assertFrame(frames, 2, 9, 0, LastFrame.class);
    }

    @Test
    public void shouldGetFramesForOneGame() {
        List<String> pins = getListOfPins(20, "1");

        List<Frame> frames = new DefaultFrameService().getFramesForPins(pins);

        assertFrames(frames, 2, 2);
        assertFrame(frames, 2, 9, 2, LastFrame.class);
    }

    @Test
    public void shouldGetFramesForFiveGame() {
        List<String> pins = getListOfPins(21, "5");

        List<Frame> frames = new DefaultFrameService().getFramesForPins(pins);

        assertFrames(frames, 10, 2);
        assertFrame(frames, 3, 9, 15, LastFrame.class);
    }

    @Test
    public void shouldGetFramesForPerfectGame() {
        List<String> pins = getListOfPins(12, "10");

        List<Frame> frames = new DefaultFrameService().getFramesForPins(pins);

        assertFrames(frames, 10, 1);
        assertFrame(frames, 3, 9, 30, LastFrame.class);
    }

    private List<String> getListOfPins(int size, String value) {
        List<String> pins = new ArrayList<>();
        for (int i = 0; i < size; i ++) {
            pins.add(value);
        }
        return pins;
    }

    private void assertFrames(List<Frame> frames, int value, int pins) {
        assertFalse(frames.isEmpty());
        assertEquals(10, frames.size());
        for (int i = 0; i < 9; i++) {
            assertFrame(frames, pins, i, value, Frame.class);
            assertNotNull(frames.get(i).getNextFrame());
        }
    }

    private void assertFrame(List<Frame> frames, int pins, int index, int value, Class clas) {
        assertEquals(pins, frames.get(index).getPinfalls().size());
        assertEquals(value, frames.get(index).getValue());
        assertTrue(clas.isInstance(frames.get(index)));
    }
}
