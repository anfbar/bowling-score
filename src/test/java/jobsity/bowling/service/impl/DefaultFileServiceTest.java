package jobsity.bowling.service.impl;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class DefaultFileServiceTest {

    private static final String PLAYER = "Carl";
    private static final String FILE = "scores-test.txt";
    private static final int EXPECTED_PINS = 12;

    @Test
    public void shouldReadFile() throws IOException, URISyntaxException {
        Map<String, List<String>> pinsFromFile =
                new DefaultFileService(FILE)
                        .getPinsFromFile();

        assertFalse(pinsFromFile.isEmpty());
        List<String> playerPins = pinsFromFile.get(PLAYER);
        assertNotNull(playerPins);
        assertEquals(EXPECTED_PINS, playerPins.size());
    }
}
