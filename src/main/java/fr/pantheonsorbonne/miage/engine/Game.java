package fr.pantheonsorbonne.miage.engine;


import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.DiscardPile;
import fr.pantheonsorbonne.miage.player.Player;

public interface Game {

    GameResult gameStatus();

    Player getNextPlayer();

    void start(DiscardPile discardPile, DeckPile deckPile);

}