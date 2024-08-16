package com.broadcom.demo.repositories;

import com.broadcom.demo.entities.Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

    @Query(value =
            "SELECT winCounts.winCount, GROUP_CONCAT(winCounts.playerId) AS playerIds " +
                    "FROM (" +
                    "    SELECT p.id AS playerId, COALESCE(COUNT(m.id), 0) AS winCount " +
                    "    FROM playersinfo p " +
                    "    LEFT JOIN matches m ON p.id = m.winner_id " +
                    "    GROUP BY p.id" +
                    ") AS winCounts " +
                    "GROUP BY winCounts.winCount " +
                    "ORDER BY winCounts.winCount",
            nativeQuery = true)
    List<Map<String, Object>> findPlayerWinCounts();
}
