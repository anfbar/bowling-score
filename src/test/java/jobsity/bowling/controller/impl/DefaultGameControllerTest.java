package jobsity.bowling.controller.impl;

import jobsity.bowling.config.TestConfig;
import jobsity.bowling.controller.GameController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.URISyntaxException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class DefaultGameControllerTest {

    @Autowired
    private GameController gameController;

    @Test
    public void shouldCalculateScores() throws IOException, URISyntaxException {
        gameController.startGame();
    }
}
