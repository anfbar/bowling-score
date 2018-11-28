package jobsity.bowling.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public interface FileService {
    Map<String, List<String>> getPinsFromFile() throws IOException, URISyntaxException;
}
