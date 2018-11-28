package jobsity.bowling.service;

import jobsity.bowling.model.Frame;

import java.util.List;

public interface ScoreService {
    void setFramesScore(List<Frame> frames);
}
