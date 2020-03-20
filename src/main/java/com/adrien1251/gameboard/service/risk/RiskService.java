package com.adrien1251.gameboard.service.risk;

import com.adrien1251.gameboard.bo.risk.RiskBoard;
import com.adrien1251.gameboard.bo.risk.RiskCard;
import com.adrien1251.gameboard.exceptions.ApplicationException;
import com.adrien1251.gameboard.repository.risk.RiskRepository;
import com.adrien1251.gameboard.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class RiskService {
    private final int DECK_SIZE = 30;

    private final RiskRepository riskRepository;

    private final CardService cardService;

    @Autowired
    public RiskService(RiskRepository riskRepository, CardService cardService) {
        this.riskRepository = riskRepository;
        this.cardService = cardService;
    }

    public RiskBoard createBoard(int nbPlayer) {
        List<RiskCard> deck = cardService.shuffleAndGet(DECK_SIZE);

        return riskRepository.createBoard(nbPlayer, shuffleTheEndCardToTheEndOfDeck(deck, nbPlayer));
    }

    public RiskBoard getBoard(UUID uuid) {
        return riskRepository.getBoard(uuid);
    }

    public RiskCard pickCard(UUID uuid) {
        return riskRepository.getBoard(uuid).pickAndNext();
    }

    private List<RiskCard> shuffleTheEndCardToTheEndOfDeck(List<RiskCard> deck, int nbPlayer) {
        RiskCard theEndCard = deck.stream()
                .filter((card) -> card.getName().equals("THE END"))
                .findFirst().orElseThrow(() -> new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur est survenu lors du trie des cartes"));

        if(deck.indexOf(theEndCard) < nbPlayer * 4) {
            deck.remove(theEndCard);
            deck.add(new Random().nextInt(deck.size() - ( (nbPlayer * 4) - 1)) +  (nbPlayer * 4), theEndCard);
        }

        return deck;
    }
}
