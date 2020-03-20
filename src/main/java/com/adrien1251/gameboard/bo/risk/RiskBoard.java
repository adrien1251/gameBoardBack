package com.adrien1251.gameboard.bo.risk;

import com.adrien1251.gameboard.exceptions.ApplicationException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RiskBoard implements Serializable {
    private List<RiskCard> deck;
    private UUID uuid;
    private int nbPlayer;

    private int actualCardIndex;

    public RiskBoard(int nbPlayer, List<RiskCard> deck) {
        this.uuid = UUID.randomUUID();
        this.actualCardIndex = 0;
        this.nbPlayer = nbPlayer;
        this.deck = deck;
    }

    public RiskCard pickAndNext() {
        if(actualCardIndex == deck.size()) throw new ApplicationException(HttpStatus.NOT_FOUND, "The deck is empty");
        return this.deck.get(actualCardIndex++);
    }
}
