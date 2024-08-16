package com.broadcom.demo.services;

import com.broadcom.demo.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public List<List<Long>> getAllPlayersWinCounts() {
        List<Map<String, Object>> results = matchRepository.findPlayerWinCounts();
        List<List<Long>> playerWinCounts = new ArrayList<>();

        // Process the query results
        for (Map<String, Object> result : results) {
            Integer winCount = ((Number) result.get("winCount")).intValue();
            String playerIdsString = (String) result.get("playerIds");

            // Split player IDs by comma
            List<Long> playerIds = new ArrayList<>();
            for (String id : playerIdsString.split(",")) {
                playerIds.add(Long.parseLong(id));
            }

            // Ensure the playerWinCounts list has enough capacity
            while (playerWinCounts.size() <= winCount) {
                playerWinCounts.add(new ArrayList<>());
            }

            playerWinCounts.get(winCount).addAll(playerIds);
        }

        return playerWinCounts;
    }
}
