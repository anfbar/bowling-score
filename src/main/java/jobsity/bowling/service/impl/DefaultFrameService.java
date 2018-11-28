package jobsity.bowling.service.impl;

import jobsity.bowling.model.Frame;
import jobsity.bowling.model.LastFrame;
import jobsity.bowling.service.FrameService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultFrameService implements FrameService {

    @Override
    public List<Frame> getFramesForPins(List<String> pins) {
        List<Frame> frames = new ArrayList<>();
        for (String pin : pins) {
            if (isLatestFrameComplete(frames)) {
                addNewFrame(frames, pin);
            } else {
                getLatestFrame(frames).getPinfalls().add(pin);
            }
        }
        return frames;
    }

    private boolean isLatestFrameComplete(List<Frame> frames) {
        return Optional.ofNullable(getLatestFrame(frames))
                .map(Frame::isComplete)
                .orElse(true);
    }

    private Frame getLatestFrame(List<Frame> frames) {
        if (!frames.isEmpty()) {
            return frames.get(frames.size() - 1);
        }
        return null;
    }

    private void addNewFrame(List<Frame> frames, String pin) {
        Frame frame;
        if (frames.size() == 9) {
            frame = new LastFrame(pin);
        } else {
            frame = new Frame(pin);
        }
        Optional.ofNullable(getLatestFrame(frames))
                .ifPresent(f -> f.setNextFrame(frame));
        frames.add(frame);
    }
}
