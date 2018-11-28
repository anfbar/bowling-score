package jobsity.bowling.service.impl;

import jobsity.bowling.model.Frame;
import jobsity.bowling.service.ScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultScoreService implements ScoreService {

    @Override
    public void setFramesScore(List<Frame> frames) {
        int score = 0;
        for (Frame frame : frames) {
            if (frame.isStrike()) {
                score += frame.getValue() + frame.getNextPinValue() + frame.getSecondNextPinValue();
            } else if (frame.isSpare()) {
                score += frame.getValue() + frame.getNextPinValue();
            } else {
                score += frame.getValue();
            }
            frame.setScore(score);
        }
    }
}
