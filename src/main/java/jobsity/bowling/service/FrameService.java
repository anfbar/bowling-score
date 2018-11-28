package jobsity.bowling.service;

import jobsity.bowling.model.Frame;

import java.util.List;

public interface FrameService {
    List<Frame> getFramesForPins(List<String> pins);
}
