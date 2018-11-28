package jobsity.bowling.service.impl;

import jobsity.bowling.model.Frame;
import jobsity.bowling.model.Player;
import jobsity.bowling.service.FormatService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Service
public class DefaultFormatService implements FormatService {

    private String frameHeader;
    private String scoreHeader;
    private String pinfallsHeader;

    public DefaultFormatService(@Value("${frame.header}") String frameHeader
            , @Value("${score.header}") String scoreHeader
            , @Value("${pinfalls.header}") String pinfallsHeader) {
        this.frameHeader = frameHeader;
        this.scoreHeader = scoreHeader;
        this.pinfallsHeader = pinfallsHeader;
    }

    @Override
    public List<String> getFormatForPlayers(List<Player> players) {
        List<String> playersFormat = players.stream()
                .map(this::getFormattedPlayer)
                .flatMap(List::stream)
                .collect(toList());
        playersFormat.add(0, "\n" + getFramesHeader());
        return playersFormat;
    }

    private List<String> getFormattedPlayer(Player player) {
        String pinfalls = pinfallsHeader + "\t" + getFormattedPinfalls(player);
        String scores = scoreHeader + "\t\t" + getFormattedScore(player);
        return asList(player.getName(), pinfalls, scores);
    }

    private String getFormattedPinfalls(Player player) {
        return player.getFrames().stream()
                .map(Frame::toString)
                .collect(joining("\t"));
    }

    private String getFormattedScore(Player player) {
        return player.getFrames().stream()
                .map(Frame::getScore)
                .map(String::valueOf)
                .collect(joining("\t\t"));
    }

    private String getFramesHeader() {
        StringBuilder frames = new StringBuilder(frameHeader);
        for (int i = 0; i < 10; i++) {
            frames.append("\t\t");
            frames.append(i + 1);
        }
        return frames.toString();
    }
}
