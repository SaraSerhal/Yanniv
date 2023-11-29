package fr.pantheonsorbonne.miage.engine;

import java.util.List;

import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.DiscardPile;
import fr.pantheonsorbonne.miage.player.Player;

public interface Game {

    Player getNextPlayer(List<Player> players);

    void goNextRound(DiscardPile discardPile, DeckPile deckPile);

    void goNextPlayer(Player currentPlayer,DiscardPile discardPile, DeckPile deckPile);

    void start(DiscardPile discardPile, DeckPile deckPile);

    void endOfRound(Player currentPlayer);

}