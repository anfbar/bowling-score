package jobsity.bowling.model;

public class LastFrame extends Frame {

    public LastFrame(String pins) {
        super(pins);
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public int getNextPinValue() {
        return 0;
    }

    @Override
    public int getSecondNextPinValue() {
        return 0;
    }

    @Override
    public String toString() {
        String response = super.toString();
        if (isStrike()) {
            response = "X\t" + getPinfalls().get(1) + "\t" + getPinfalls().get(2);
        } else if (isSpare()) {
            response = response + "\t" + getPinfalls().get(2);
        }
        return response.replaceAll("10", "X");
    }
}
