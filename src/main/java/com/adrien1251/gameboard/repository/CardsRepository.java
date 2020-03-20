package com.adrien1251.gameboard.repository;

import com.adrien1251.gameboard.bo.risk.RiskCard;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CardsRepository {
    /**
     * Cards load by json file
     */
    private List<RiskCard> cards;

    public CardsRepository() {
        try {
            var cardsStream = this.getClass().getResourceAsStream("/cards.json");
            this.cards = Arrays.asList(
                    new ObjectMapper().readValue(cardsStream, RiskCard[].class)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<RiskCard> getCards() {
        return new ArrayList<>(cards);
    }

}
