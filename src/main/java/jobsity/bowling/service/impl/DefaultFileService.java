package jobsity.bowling.service.impl;

import jobsity.bowling.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

@Service
public class DefaultFileService implements FileService {

    private String fileName;

    public DefaultFileService(@Value("${file.name}") String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Map<String, List<String>> getPinsFromFile() throws IOException, URISyntaxException {
        try (BufferedReader reader = new BufferedReader(getStreamForFile())) {
            return getPinsGroupedByPlayer(reader.lines());
        }
    }

    private InputStreamReader getStreamForFile() {
        return new InputStreamReader(getClass()
                .getResourceAsStream("/" + fileName));
    }

    private Map<String, List<String>> getPinsGroupedByPlayer(Stream<String> lines) {
        return lines.map(line -> line.split("\\t"))
                .collect(groupingBy(array -> array[0]
                        , mapping(array -> array[1], toList())));
    }
}
