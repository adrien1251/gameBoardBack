package com.adrien1251.gameboard.service.risk;

import com.adrien1251.gameboard.bo.risk.RiskBoard;
import com.adrien1251.gameboard.bo.risk.RiskCard;
import com.adrien1251.gameboard.exceptions.ApplicationException;
import com.adrien1251.gameboard.repository.risk.RiskRepository;
import com.adrien1251.gameboard.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RiskService {
    private final int DECK_SIZE = 30;

    private final RiskRepository riskRepository;

    private final CardService cardService;

    private final List<String> cardsToMove = Arrays.asList("THE END", "JE VAIS TE NIQUER", "CHARGEZZZZ");

    @Autowired
    public RiskService(RiskRepository riskRepository, CardService cardService) {
        this.riskRepository = riskRepository;
        this.cardService = cardService;
    }

    public RiskBoard createBoard(int nbPlayer) {
        List<RiskCard> deck = cardService.shuffleAndGet(DECK_SIZE);

        return riskRepository.createBoard(nbPlayer, shuffleCardToTheEndOfDeck(deck, nbPlayer));
    }

    public RiskBoard getBoard(UUID uuid) {
        return riskRepository.getBoard(uuid);
    }

    public RiskCard pickCard(UUID uuid) {
        return riskRepository.getBoard(uuid).pickAndNext();
    }

    private List<RiskCard> shuffleCardToTheEndOfDeck(List<RiskCard> deck, int nbPlayer) {
        boolean moved;
        do {
            moved = false;
            for (String cardName : cardsToMove) {
                moved = moveToTheEnd(deck, nbPlayer, cardName) || moved;
            }
        } while (moved);
        return deck;
    }

    private boolean moveToTheEnd(List<RiskCard> deck, int nbPlayer, String cardName) {
        boolean move = false;
        RiskCard cardToMove = findCarte(deck, cardName);
        do {
            if (deck.indexOf(cardToMove) < nbPlayer * 4) {
                deck.remove(cardToMove);
                deck.add(new Random().nextInt(deck.size() - ((nbPlayer * 4) - 1)) + (nbPlayer * 4), cardToMove);
                move = true;
            }

            cardToMove = findCarte(deck, cardName);
        } while (deck.indexOf(cardToMove) < nbPlayer * 4);


        return move;
    }

    private RiskCard findCarte(List<RiskCard> deck, String cardName) {
        return deck.stream()
                .filter((card) -> card.getName().equals(cardName))
                .findFirst().orElseThrow(() -> new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur est survenu lors du trie des cartes"));
    }
}
