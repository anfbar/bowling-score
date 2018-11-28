package jobsity.bowling.controller.impl;

import jobsity.bowling.controller.GameController;
import jobsity.bowling.model.Frame;
import jobsity.bowling.model.Player;
import jobsity.bowling.service.FileService;
import jobsity.bowling.service.FormatService;
import jobsity.bowling.service.FrameService;
import jobsity.bowling.service.ScoreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static java.lang.String.join;
import static java.util.stream.Collectors.toList;

@Controller
public class DefaultGameController implements GameController {

    private static final Logger LOGGER = LogManager.getLogger(DefaultGameController.class);

    private FileService fileService;
    private FrameService frameService;
    private ScoreService scoreService;
    private FormatService formatService;

    @Autowired
    public DefaultGameController(FileService fileService, FrameService frameService, ScoreService scoreService, FormatService formatService) {
        this.fileService = fileService;
        this.frameService = frameService;
        this.scoreService = scoreService;
        this.formatService = formatService;
    }

    @Override
    public void startGame() throws IOException, URISyntaxException {
        Map<String, List<String>> pinsFromFile = fileService.getPinsFromFile();

        List<Player> players = pinsFromFile.entrySet()
                .stream()
                .map(this::getPlayer)
                .collect(toList());

        LOGGER.info(join("\n", formatService.getFormatForPlayers(players)));
    }

    private Player getPlayer(Map.Entry<String, List<String>> entry) {
        List<Frame> frames = frameService.getFramesForPins(entry.getValue());
        scoreService.setFramesScore(frames);
        return new Player(entry.getKey(), frames);
    }
}
