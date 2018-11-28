package jobsity.bowling.service;

import jobsity.bowling.model.Player;

import java.util.List;

public interface FormatService {
    List<String> getFormatForPlayers(List<Player> players);
}
