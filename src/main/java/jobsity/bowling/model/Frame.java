package jobsity.bowling.model;

import jobsity.bowling.util.IntegerUtils;

import java.util.ArrayList;
import java.util.List;

import static jobsity.bowling.util.IntegerUtils.parseInt;
import static jobsity.bowling.util.IntegerUtils.sumStrings;

public class Frame {

    private List<String> pinfalls;
    private int score;
    private Frame nextFrame;

    public Frame(String pins) {
        pinfalls = new ArrayList<>();
        pinfalls.add(pins);
        score = 0;
    }

    public boolean isComplete() {
        return getValue() == 10 || getPinfalls().size() == 2;
    }

    public boolean isStrike() {
        return parseInt(getPinfalls().get(0)) == 10;
    }

    public boolean isSpare() {
        return sumStrings(getPinfalls().get(0), getPinfalls().get(1)) == 10;
    }

    public int getValue() {
        return pinfalls.stream()
                .mapToInt(IntegerUtils::parseInt)
                .sum();
    }

    public int getNextPinValue() {
        return parseInt(nextFrame.getPinfalls().get(0));
    }

    public int getSecondNextPinValue() {
        if (nextFrame.isStrike() && !(nextFrame instanceof LastFrame)) {
            return nextFrame.getNextPinValue();
        }
        return parseInt(nextFrame.getPinfalls().get(1));
    }

    public List<String> getPinfalls() {
        return pinfalls;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Frame getNextFrame() {
        return nextFrame;
    }

    public void setNextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

    @Override
    public String toString() {
        if (isStrike()) {
            return "\tX";
        } else if (isSpare()) {
            return pinfalls.get(0) + "\t/";
        }
        return pinfalls.get(0) + "\t" + pinfalls.get(1);
    }
}
