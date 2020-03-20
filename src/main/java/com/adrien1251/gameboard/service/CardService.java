package com.adrien1251.gameboard.service;

import com.adrien1251.gameboard.bo.risk.RiskCard;
import com.adrien1251.gameboard.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CardService {
    private CardsRepository cardsRepository;

    @Autowired
    public CardService(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    public List<RiskCard> shuffleAndGet(int nb) {
        List<RiskCard> riskCards = cardsRepository.getCards();
        Collections.shuffle(riskCards);
        return riskCards;
    }
}
