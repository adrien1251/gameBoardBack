package com.adrien1251.gameboard.repository.risk;

import com.adrien1251.gameboard.bo.risk.RiskBoard;
import com.adrien1251.gameboard.bo.risk.RiskCard;
import com.adrien1251.gameboard.exceptions.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RiskRepository {
    /**
     * All risk game
     */
    private Map<UUID, RiskBoard> games = new HashMap<>();

    public RiskBoard createBoard(int nbPlayer, List<RiskCard> riskCards) {
        RiskBoard riskBoard = new RiskBoard(nbPlayer, riskCards);
        games.put(riskBoard.getUuid(), riskBoard);
        return riskBoard;
    }

    public RiskBoard getBoard(UUID uuid) {
        if(games.containsKey(uuid)) {
            return games.get(uuid);
        }
        throw new ApplicationException(HttpStatus.NOT_FOUND, "Board " + uuid.toString() + " not found");
    }
}