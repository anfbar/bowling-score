package jobsity.bowling.model;

import java.util.List;

public class Player {

    private String name;
    private List<Frame> frames;

    public Player(String name, List<Frame> frames) {
        this.name = name;
        this.frames = frames;
    }

    public String getName() {
        return name;
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
